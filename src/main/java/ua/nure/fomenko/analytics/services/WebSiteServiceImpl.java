package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.dao.WebSiteDao;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.db.entity.dto.WebSiteDto;
import ua.nure.fomenko.analytics.transaction.Transaction;
import ua.nure.fomenko.analytics.transaction.TransactionManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fomenko on 18.03.2017.
 */
public class WebSiteServiceImpl implements WebSiteService {

    private WebSiteDao webSiteDao;
    private TransactionManager transactionManager;

    public WebSiteServiceImpl(WebSiteDao webSiteDao, TransactionManager transactionManager) {
        this.webSiteDao = webSiteDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public int addWebSite(WebSite webSite) {
        return transactionManager.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return webSiteDao.create(webSite);
            }
        });
    }

    @Override
    public WebSite getWebSiteByUrl(String url) {
        return transactionManager.execute(new Transaction<WebSite>() {
            @Override
            public WebSite execute() throws SQLException {
                return webSiteDao.getWebSiteByUrl(url);
            }
        });
    }

    @Override
    public WebSite getWebSiteById(int id) {
        return transactionManager.execute(new Transaction<WebSite>() {
            @Override
            public WebSite execute() throws SQLException {
                return webSiteDao.get(id);
            }
        });
    }

    @Override
    public List<WebSite> getWebSitesByUser(User user) {
        return transactionManager.execute(new Transaction<List<WebSite>>() {
            @Override
            public List<WebSite> execute() throws SQLException {
                return webSiteDao.getWebSiteByUser(user);
            }
        });
    }

    @Override
    public boolean updateWebSite(WebSite webSite) {
        return transactionManager.execute(new Transaction<Boolean>() {
            @Override
            public Boolean execute() throws SQLException {
                return webSiteDao.update(webSite);
            }
        });
    }

    @Override
    public boolean deleteWebSite(WebSite webSite) {
        return transactionManager.execute(new Transaction<Boolean>() {
            @Override
            public Boolean execute() throws SQLException {
                return webSiteDao.delete(webSite);
            }
        });
    }

    @Override
    public boolean isExistByUrl(String url) {
        return transactionManager.execute(new Transaction<Boolean>() {
            @Override
            public Boolean execute() throws SQLException {
                return webSiteDao.isExistWebSiteByURL(url);
            }
        });
    }

    @Override
    public WebSite getRegisterWebSiteFromDto(WebSiteDto webSiteDto) {
        WebSite webSite = new WebSite();
        webSite.setUrl(webSiteDto.getUrl());
        return webSite;
    }
}
