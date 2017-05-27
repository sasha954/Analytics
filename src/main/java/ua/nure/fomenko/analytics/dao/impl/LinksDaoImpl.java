package ua.nure.fomenko.analytics.dao.impl;

import org.apache.log4j.Logger;
import sun.plugin2.message.Message;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.SQLQueries;
import ua.nure.fomenko.analytics.dao.LinksDao;
import ua.nure.fomenko.analytics.dao.WebSiteDao;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.exception.DBException;
import ua.nure.fomenko.analytics.services.DefaultKeyConverterServiceImpl;
import ua.nure.fomenko.analytics.services.KeyConverterService;
import ua.nure.fomenko.analytics.transaction.ThreadLockHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fomenko on 20.03.2017.
 */
public class LinksDaoImpl implements LinksDao {

    public static final Logger LOG = Logger.getLogger(LinksDaoImpl.class);

    @Override
    public Links get(int id) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        Links link = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.LINK_GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                link = parseLinks(resultSet);
                LOG.debug(Messages.LINK_OBTAINED);
            }
            resultSet.close();
            return link;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_OBTAIN_LINK;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public int create(Links entity) {
        LOG.debug(Messages.LINK_START_CREATING);
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        int resultId = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.LINK_CREATE_NEW,
                Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, entity.getUrl());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getWebSite().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                resultId = resultSet.getInt(1);
            }
            resultSet.close();
            LOG.debug(Messages.LINK_WAS_CREATED);
            return resultId;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_CREATE_LINK;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public boolean update(Links entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Links entity) {
        Connection connection = ThreadLockHandler.getConnection();
        int result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.LINK_DELETE_BY_ID)){
            preparedStatement.setInt(1, entity.getId());
            result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e){
            LOG.error(e);
            throw new DBException();
        }
    }

    @Override
    public boolean isExistLinkByUrl(String url) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.LINK_GET_BY_URL)){
            preparedStatement.setString(1, url);
            resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            String message = Messages.ERR_CANNOT_OBTAIN_LINK;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public Links getLinkByUrl(String url) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        Links link = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.LINK_GET_BY_URL)){
            statement.setString(1, url);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                link = parseLinks(resultSet);
                LOG.debug(Messages.LINK_OBTAINED);
            }
            resultSet.close();
            return link;

        }catch (SQLException e){
            String message = Messages.ERR_CANNOT_OBTAIN_LINK;
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    @Override
    public List<Links> getLinksByWebSite(WebSite webSite) {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet = null;
        List<Links> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.LINKS_GET_BY_WEBSITE)){
            preparedStatement.setInt(1, webSite.getId());
            resultSet = preparedStatement.executeQuery();
            list = getLinkList(resultSet);
            LOG.debug("Links was obtained from database");
            return list;
        } catch (SQLException e) {
            String message = "Cannot obtain links from database";
            LOG.error(message, e);
            throw new DBException(message, e);
        }
    }

    private Links parseLinks(ResultSet resultSet) throws SQLException {
        Links link = new Links();
        WebSiteDao webSiteDao = new WebSiteDaoImpl();
        link.setId(resultSet.getInt(Params.ID));
        link.setUrl(resultSet.getString(Params.LINK_URL));
        link.setName(resultSet.getString(Params.LINK_NAME));
        link.setWebSite(webSiteDao.get(resultSet.getInt(Params.LINK_WEB_SITE_ID)));
        return link;
    }

    private List<Links> getLinkList(ResultSet resultSet) throws SQLException{
        List<Links> list = new ArrayList<>();
        Links link = new Links();
        KeyConverterService keyConverterService = new DefaultKeyConverterServiceImpl();
        while (resultSet.next()) {
           link = parseLinks(resultSet);
           link.setCountVisiters(resultSet.getLong("countVisiters"));
           link.setParamForUrlOnWebSite(keyConverterService.idToKey(link.getId()));
           list.add(link);
        }
        resultSet.close();
        return list;
    }
}
