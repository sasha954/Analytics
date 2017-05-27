package ua.nure.fomenko.analytics.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.SQLQueries;
import ua.nure.fomenko.analytics.dao.UserDao;
import ua.nure.fomenko.analytics.dao.WebSiteDao;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.exception.DBException;
import ua.nure.fomenko.analytics.transaction.ThreadLockHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fomenko on 17.03.2017.
 */
public class WebSiteDaoImpl implements WebSiteDao {
    private static final Logger LOG = Logger.getLogger(WebSiteDaoImpl.class);

    @Override
    public WebSite get(int id) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        WebSite webSite = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.WEB_SITE_GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                webSite = parseWebSite(resultSet);
                LOG.debug(Messages.WEB_SITE_OBTAINED);
            }
            resultSet.close();
            return webSite;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_OBTAIN_WEB_SITE;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public boolean isExistWebSiteByURL(String url) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.WEB_SITE_GET_BY_URL)) {
            preparedStatement.setString(1, url);
            resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_CHECK_WEB_SITE;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public int create(WebSite entity) {
        LOG.debug(Messages.WEB_SITE_START_CREATING);
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        int resultId = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.WEB_SITE_CREATE_NEW,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getUrl());
            preparedStatement.setInt(2, entity.getUser().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                resultId = resultSet.getInt(1);
            }
            resultSet.close();
            LOG.debug(Messages.WEB_SITE_WAS_CREATED);
            return resultId;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_CREATE_WEB_SITE;
            LOG.error(message, e);
            throw new DBException(message, e);
        }

    }

    @Override
    public boolean update(WebSite entity) {
        LOG.debug(Messages.WEB_SITE_START_CREATING);
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.WEB_SITE_UPDATE,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getUrl());
            preparedStatement.setInt(2, entity.getUser().getId());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                result = true;
            }
            resultSet.close();
            LOG.debug(Messages.WEB_SITE_WAS_UPDATED);
            return result;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_UPDATE_WEB_SITE;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public List<WebSite> getWebSiteByUser(User user) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        List<WebSite> webSiteList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.WEB_SITES_GET_BY_USER)) {
            preparedStatement.setInt(1, user.getId());
            resultSet = preparedStatement.executeQuery();
            webSiteList = getWebSitesList(resultSet);
            LOG.debug("WebSite list was obtained");
            return webSiteList;
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public WebSite getWebSiteByUrl(String url) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        WebSite webSite = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.WEB_SITE_GET_BY_URL)) {
            preparedStatement.setString(1, url);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                webSite = parseWebSite(resultSet);
                LOG.debug(Messages.WEB_SITE_OBTAINED);
            }
            resultSet.close();
            return webSite;

        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_OBTAIN_WEB_SITE;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public boolean delete(WebSite entity) {
        Connection connection = ThreadLockHandler.getConnection();
        int result = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.WEB_SITE_DELETE_BY_ID)){
            preparedStatement.setInt(1, entity.getId());
            result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException();
        }
    }

    private WebSite parseWebSite(ResultSet resultSet) throws SQLException {
        WebSite webSite = new WebSite();
        UserDao userDao = new UserDaoImpl();
        webSite.setId(resultSet.getInt(Params.ID));
        webSite.setUrl(resultSet.getString(Params.WEBSITE_URL));
        webSite.setUser(userDao.get(resultSet.getInt(Params.WEBSITE_USER_ID)));
        return webSite;
    }

    private List<WebSite> getWebSitesList(ResultSet resultSet) throws SQLException {
        List<WebSite> webSiteList = new ArrayList<>();
        while (resultSet.next()) {
            webSiteList.add(parseWebSite(resultSet));
        }
        resultSet.close();
        return webSiteList;
    }

}
