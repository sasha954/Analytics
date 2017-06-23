package ua.nure.fomenko.analytics.dao;

import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;
import ua.nure.fomenko.analytics.db.entity.Visiters;

import java.util.List;

/**
 * Created by fomenko on 25.03.2017.
 */
public interface VisitersDao extends GenericDao<Visiters> {

    public List<Visiters> getVisitersByCountryAndLinks(String country);
    public List<Visiters> getVisitersByStatistic(List<Statistic> statistics);
    public boolean isExistByIp(String ip);
    public Visiters getVisiterByIp(String ip);

}
