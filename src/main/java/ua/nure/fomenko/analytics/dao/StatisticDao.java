package ua.nure.fomenko.analytics.dao;

import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;

import java.util.List;

/**
 * Created by fomenko on 27.05.2017.
 */
public interface StatisticDao extends GenericDao<Statistic> {
    List<Statistic> getStatisticsByLinkId(int linkId);

}
