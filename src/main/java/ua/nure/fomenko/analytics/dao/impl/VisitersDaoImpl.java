package ua.nure.fomenko.analytics.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.SQLQueries;
import ua.nure.fomenko.analytics.dao.VisitersDao;
import ua.nure.fomenko.analytics.db.entity.Visiters;
import ua.nure.fomenko.analytics.exception.DBException;
import ua.nure.fomenko.analytics.transaction.ThreadLockHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fomenko on 27.03.2017.
 */
public class VisitersDaoImpl implements VisitersDao {
    private static final Logger LOG = Logger.getLogger(VisitersDaoImpl.class);

    @Override
    public Visiters get(int id) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        Visiters visiter = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.VISITER_GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                visiter = parseVisiter(resultSet);
                LOG.debug(Messages.VISITER_OBTAINED);
            }
            resultSet.close();
            return visiter;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_OBTAIN_VISITER;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public int create(Visiters entity) {
        LOG.debug(Messages.VISITERS_START_CREATING);
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        int resultId = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.VISITER_CREATE_NEW,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getCountry_code());
            preparedStatement.setString(2, entity.getCountry());
            preparedStatement.setString(3, entity.getCountry_rus());
            preparedStatement.setString(4, entity.getRegion());
            preparedStatement.setString(5, entity.getRegion_rus());
            preparedStatement.setString(6, entity.getCity());
            preparedStatement.setString(7, entity.getCity_rus());
            preparedStatement.setString(8, entity.getLatitude());
            preparedStatement.setString(9, entity.getLongitude());
            preparedStatement.setInt(10, entity.getZip_code());
            preparedStatement.setString(11, entity.getTime_zone());
            preparedStatement.setString(12, entity.getIp());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                resultId = resultSet.getInt(1);
            }
            resultSet.close();
            LOG.debug(Messages.VISITER_WAS_CREATED);
            return resultId;

        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_CREATE_VISITER;
            LOG.error(message, e);
            throw new DBException(message, e);
        }

    }

    @Override
    public List<Visiters> getVisitersByCountryAndLinks(String country) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        List<Visiters> visitersList = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.VISITERS_GET_BY_COUNTRY)) {
            preparedStatement.setString(1, country);
            resultSet = preparedStatement.executeQuery();
            visitersList = getVisitersList(resultSet);
            LOG.debug("Visiters was obtained from database");
            return visitersList;
        } catch (SQLException e) {
            String message = "Cannot obtain visiters from database";
            LOG.error(message, e);
            throw new DBException(message, e);

        }
    }

    @Override
    public boolean isExistByIp(String ip) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.VISITER_GET_BY_IP)) {
            statement.setString(1, ip);
            resultSet = statement.executeQuery();

            boolean result = resultSet.next();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_CHECK_VISITER;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public Visiters getVisiterByIp(String ip) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        Visiters visiters = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.VISITER_GET_BY_IP)){
            statement.setString(1, ip);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                visiters = parseVisiter(resultSet);
                LOG.debug(Messages.VISITER_OBTAINED);
            }
            resultSet.close();
            return visiters;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_OBTAIN_VISITER;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public boolean update(Visiters entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Visiters entity) {
        throw new UnsupportedOperationException();
    }

    private List<Visiters> getVisitersList(ResultSet resultSet) throws SQLException {
        List<Visiters> visitersList = new ArrayList<>();
        while (resultSet.next()) {
            visitersList.add(parseVisiter(resultSet));
        }
        resultSet.close();
        return visitersList;
    }

    private Visiters parseVisiter(ResultSet resultSet) throws SQLException {
        Visiters visiter = new Visiters();
        visiter.setId(resultSet.getInt(Params.ID));
        visiter.setCity(resultSet.getString(Params.VISITERS_CITY));
        visiter.setCity_rus(resultSet.getString(Params.VISITERS_CITY_RUS));
        visiter.setCountry_code(resultSet.getString(Params.VISITERS_COUNTRY_CODE));
        visiter.setCountry(resultSet.getString(Params.VISITERS_COUNTRY));
        visiter.setCountry_rus(resultSet.getString(Params.VISITERS_COUNTRY_RUS));
        visiter.setRegion(resultSet.getString(Params.VISITERS_REGION));
        visiter.setRegion_rus(resultSet.getString(Params.VISITERS_REGION_RUS));
        visiter.setLatitude(resultSet.getString(Params.VISITERS_LATITUDE));
        visiter.setLongitude(resultSet.getString(Params.VISITERS_LONGITUDE));
        visiter.setZip_code(resultSet.getInt(Params.VISITERS_ZIP_CODE));
        visiter.setTime_zone(resultSet.getString(Params.VISITERS_TIME_ZONE));
        visiter.setIp(resultSet.getString(Params.VISITERS_IP));
        return visiter;
    }
}
