package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.dao.LinksDao;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.db.entity.dto.LinkDto;
import ua.nure.fomenko.analytics.transaction.Transaction;
import ua.nure.fomenko.analytics.transaction.TransactionManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fomenko on 05.04.2017.
 */
public class LinksServiceImpl implements LinksService {

    private TransactionManager transactionManager;
    private LinksDao linksDao;

    public LinksServiceImpl(LinksDao linksDao, TransactionManager transactionManager){
        this.transactionManager = transactionManager;
        this.linksDao = linksDao;
    }

    @Override
    public int addLink(Links link) {
        return transactionManager.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return linksDao.create(link);
            }
        });
    }

    @Override
    public Links getLinkByUrl(String url) {
        return transactionManager.execute(new Transaction<Links>() {
            @Override
            public Links execute() throws SQLException {
                return linksDao.getLinkByUrl(url);
            }
        });
    }

    @Override
    public Links getLinkById(int id) {
        return transactionManager.execute(new Transaction<Links>() {
            @Override
            public Links execute() throws SQLException {
                return linksDao.get(id);
            }
        });
    }

    @Override
    public List<Links> getLinksByWebSite(WebSite webSite) {
        return transactionManager.execute(new Transaction<List<Links>>() {
            @Override
            public List<Links> execute() throws SQLException {
                return linksDao.getLinksByWebSite(webSite);
            }
        });
    }

    @Override
    public boolean deleteLink(Links links) {
        return transactionManager.execute(new Transaction<Boolean>() {
            @Override
            public Boolean execute() throws SQLException {
                return linksDao.delete(links);
            }
        });
    }

    @Override
    public boolean isExistByUrl(String url) {
        return transactionManager.execute(new Transaction<Boolean>() {
            @Override
            public Boolean execute() throws SQLException {
                return linksDao.isExistLinkByUrl(url);
            }
        });
    }

    @Override
    public Links getLinkFromDto(LinkDto linkDto) {
        Links link = new Links();
        link.setUrl(linkDto.getUrl());
        link.setName(linkDto.getName());

        return link;
    }
}
