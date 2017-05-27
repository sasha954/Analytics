package ua.nure.fomenko.analytics.dao;

import ua.nure.fomenko.analytics.db.entity.Visiters;

import java.util.List;

/**
 * Created by fomenko on 25.03.2017.
 */
public interface VisitersDao extends GenericDao<Visiters> {

    public List<Visiters> getVisitersByCountryAndLinks(String country);
    public boolean isExistByIp(String ip);
    public Visiters getVisiterByIp(String ip);

}
