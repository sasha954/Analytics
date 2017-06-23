package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.dao.VisitersDao;
import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;
import ua.nure.fomenko.analytics.db.entity.Visiters;
import ua.nure.fomenko.analytics.transaction.Transaction;
import ua.nure.fomenko.analytics.transaction.TransactionManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fomenko on 05.04.2017.
 */
public class VisitersServiceImpl implements VisitersService {

    private TransactionManager transactionManager;
    private VisitersDao visitersDao;

    public VisitersServiceImpl(VisitersDao visitersDao, TransactionManager transactionManager) {
        this.visitersDao = visitersDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public int addVisiter(Visiters visiters) {
        return transactionManager.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return visitersDao.create(visiters);
            }
        });
    }

    @Override
    public Visiters getVisiterById(int id) {
        return transactionManager.execute(new Transaction<Visiters>() {
            @Override
            public Visiters execute() throws SQLException {
                return visitersDao.get(id);
            }
        });
    }

    @Override
    public List<Visiters> getVisitersByCountry(String country) {
        return transactionManager.execute(new Transaction<List<Visiters>>() {
            @Override
            public List<Visiters> execute() throws SQLException {
                return visitersDao.getVisitersByCountryAndLinks(country);
            }
        });
    }

    @Override
    public List<Visiters> getVisitersByStatistics(List<Statistic> statistics) {
        return transactionManager.execute(new Transaction<List<Visiters>>() {
            @Override
            public List<Visiters> execute() throws SQLException {
               return visitersDao.getVisitersByStatistic(statistics);
            }
        });
    }

    @Override
    public boolean isExistByIp(String ip) {
        return transactionManager.execute(new Transaction<Boolean>() {
            @Override
            public Boolean execute() throws SQLException {
                return visitersDao.isExistByIp(ip);
            }
        });
    }

    @Override
    public Visiters getVisiterByIp(String ip) {
        return transactionManager.execute(new Transaction<Visiters>() {
            @Override
            public Visiters execute() throws SQLException {
                return visitersDao.getVisiterByIp(ip);
            }
        });
    }
}
