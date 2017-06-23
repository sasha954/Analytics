package ua.nure.fomenko.analytics.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.SQLQueries;
import ua.nure.fomenko.analytics.dao.UserDao;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.exception.DBException;
import ua.nure.fomenko.analytics.transaction.ThreadLockHandler;

import java.sql.*;

/**
 * Created by fomenko on 10.03.2017.
 */
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User get(int id){
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.USER_GET_BY_ID)){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = parseUser(resultSet);
                LOG.debug(Messages.USER_OBTAINED);
            }
            resultSet.close();
            return user;
        }catch (SQLException e){
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER, e);
        }
    }

    @Override
    public int create(User entity) {
        LOG.debug(Messages.USER_START_CREATING);
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        int resultId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.USER_CREATE_NEW,
                Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getPassword());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                resultId = resultSet.getInt(1);
            }
            resultSet.close();
            LOG.debug(Messages.USER_WAS_CREATED);
            return resultId;
        } catch (SQLException e){
            String message = Messages.ERR_CANNOT_CREATE_USER;
            LOG.error(message, e);
            throw new DBException(message);
        }

    }

    @Override
    public boolean update(User entity) {
        LOG.debug(Messages.USER_START_CREATING);
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.USER_UPDATE,
                Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getPassword());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                result = true;
            }
            resultSet.close();
            LOG.debug(Messages.USER_WAS_UPDATED);
            return result;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_UPDATE_USER;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public boolean delete(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isExistByEmail(String email) {
       Connection connection = ThreadLockHandler.getConnection();

       try (PreparedStatement statement = connection.prepareStatement(SQLQueries.USER_GET_BY_EMAIL)){
           statement.setString(1, email);
           ResultSet resultSet = statement.executeQuery();
           boolean result = resultSet.next();
           resultSet.close();
           return result;
       } catch (SQLException e) {
           String message = Messages.ERR_CANNOT_CHECK_USER;
           LOG.error(message, e);
           throw new DBException(message, e);
       }
    }

    @Override
    public User getUserAndCountSiteByUserEmail(String email) {
        Connection connection = ThreadLockHandler.getConnection();
        User user = null;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.USER_SITE_COUNT_GET_BY_EMAIL)){
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = parseUser(resultSet);
                user.setCountSites(resultSet.getInt(Params.USER_WEB_SITE_COUNT));
                LOG.debug(Messages.USER_OBTAINED);
            }
            resultSet.close();
            return user;
        } catch (SQLException ex){
            String message = Messages.ERR_CANNOT_OBTAIN_USER;
            LOG.error(message, ex);
            throw new DBException(message, ex);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.USER_GET_BY_EMAIL)){
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = parseUser(resultSet);
                //user.setCountSites(resultSet.getInt(Params.USER_WEB_SITE_COUNT));
                LOG.debug(Messages.USER_OBTAINED);
            }
            resultSet.close();
            return user;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_OBTAIN_USER;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public boolean isExistByEmailAndPassword(User user) {
        Connection connection = ThreadLockHandler.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(SQLQueries.USER_GET_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();
            boolean result = resultSet.next();
            resultSet.close();
            return result;
        }catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_CHECK_USER, e);
            throw new DBException(Messages.ERR_CANNOT_CHECK_USER, e);
        }

    }

    private User parseUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Params.ID));
        user.setEmail(resultSet.getString(Params.USER_EMAIL));
        user.setFirstName(resultSet.getString(Params.USER_FIRSTNAME));
        user.setLastName(resultSet.getString(Params.USER_LASTNAME));
        user.setPassword(resultSet.getString(Params.USER_PASSWORD));
        return user;
    }
}
