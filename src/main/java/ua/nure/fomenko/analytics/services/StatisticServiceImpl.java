package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.dao.StatisticDao;
import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;
import ua.nure.fomenko.analytics.transaction.Transaction;
import ua.nure.fomenko.analytics.transaction.TransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by fomenko on 31.05.2017.
 */
public class
StatisticServiceImpl implements StatisticService{
    private StatisticDao statisticDao;
    private TransactionManager transactionManager;

    public StatisticServiceImpl(StatisticDao statisticDao, TransactionManager transactionManager) {
        this.statisticDao = statisticDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public int createStatictic(Statistic statistic) {
        return transactionManager.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return statisticDao.create(statistic);
            }
        });
    }

    @Override
    public List<Statistic> getStatisticListByLinkId(int linkId) {
        return transactionManager.execute(new Transaction<List<Statistic>>() {
            @Override
            public List<Statistic> execute() throws SQLException {
                return statisticDao.getStatisticsByLinkId(linkId);
            }
        });
    }
}
