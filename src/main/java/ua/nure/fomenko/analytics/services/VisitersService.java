package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;
import ua.nure.fomenko.analytics.db.entity.Visiters;

import java.util.List;

/**
 * Created by fomenko on 05.04.2017.
 */
public interface VisitersService {

    public int addVisiter(Visiters visiters);
    public Visiters getVisiterById(int id);
    public List<Visiters> getVisitersByCountry(String country);
    public List<Visiters> getVisitersByStatistics(List<Statistic> statistics);
    public boolean isExistByIp(String ip);
    public Visiters getVisiterByIp(String ip);
}
