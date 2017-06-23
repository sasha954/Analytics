package ua.nure.fomenko.analytics.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.SQLQueries;
import ua.nure.fomenko.analytics.dao.LinksDao;
import ua.nure.fomenko.analytics.dao.StatisticDao;
import ua.nure.fomenko.analytics.dao.VisitersDao;
import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;
import ua.nure.fomenko.analytics.db.entity.Visiters;
import ua.nure.fomenko.analytics.exception.DBException;
import ua.nure.fomenko.analytics.transaction.ThreadLockHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fomenko on 27.05.2017.
 */
public class StatisticDaoImpl implements StatisticDao {
    private static final Logger LOG = Logger.getLogger(StatisticDaoImpl.class);
    public static final String MESSAGE = "Cannot obtain statistics from database";
    public static final String STATISTIC_OBTAINED = "Statistic was obtained from database";

    @Override
    public Statistic get(int id) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        Statistic statistic = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.STATISTIC_GET_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                statistic = parseStatistic(resultSet);
                LOG.debug(Messages.STATISTIC_OBTAINED);
            }
            resultSet.close();
            return statistic;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_OBTAIN_STATISTIC;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    private Statistic parseStatistic(ResultSet resultSet) throws SQLException {
        Statistic statistic = new Statistic();
        LinksDao linksDao = new LinksDaoImpl();
        VisitersDao visitersDao = new VisitersDaoImpl();
        statistic.setId(resultSet.getInt(Params.ID));
        statistic.setLinks(linksDao.get(resultSet.getInt(Params.STATISTIC_LINK_ID)));
        statistic.setVisiters(visitersDao.get(resultSet.getInt(Params.STATISTIC_VISITER_ID)));

        return statistic;
    }

    @Override
    public int create(Statistic entity) {
        LOG.debug(Messages.STATISTIC_START_CREATING);
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        int resultId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.STATISTIC_CREATE_NEW,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getVisiters().getId());
            statement.setInt(2, entity.getLinks().getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                resultId = resultSet.getInt(1);
            }
            resultSet.close();
            LOG.debug(Messages.STATISTIC_WAS_CREATED);
            return resultId;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_CREATE_STATISTIC;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public boolean update(Statistic entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Statistic entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Statistic> getStatisticsByLinkId(int linkId) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        List<Statistic> statisticList = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.STATISTIC_GET_BY_LINK_ID)) {
            statement.setInt(1, linkId);
            resultSet = statement.executeQuery();
            statisticList = getStatisticList(resultSet);
            LOG.debug(STATISTIC_OBTAINED);
            return statisticList;

        } catch (SQLException e) {
            LOG.error(MESSAGE, e);
            throw new DBException(MESSAGE, e);
        }
    }

    private Visiters parseVisiter(ResultSet resultSet) throws SQLException {
        Visiters visiter = new Visiters();
        visiter.setId(resultSet.getInt(Params.ID));
        visiter.setCity(resultSet.getString(Params.VISITERS_CITY));
        visiter.setRegion(resultSet.getString(Params.VISITERS_REGION));
        visiter.setCountry(resultSet.getString(Params.VISITERS_COUNTRY));

        return visiter;
    }

    private List<Statistic> getStatisticList(ResultSet resultSet) throws SQLException {
        List<Statistic> statisticList = new ArrayList<>();
        while (resultSet.next()) {
            Statistic statistic = new Statistic();
            statistic.setVisiters(parseVisiter(resultSet));
            statistic.setCountVisitor(resultSet.getInt("countVisitors"));
            statisticList.add(statistic);
        }
        resultSet.close();
        return statisticList;
    }
}
