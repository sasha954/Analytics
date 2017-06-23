package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;

import java.util.List;

/**
 * Created by fomenko on 31.05.2017.
 */
public interface StatisticService {
    int createStatictic(Statistic statistic);
    List<Statistic> getStatisticListByLinkId(int linkId);
}
