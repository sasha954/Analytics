package ua.nure.fomenko.analytics.dao;

import ua.nure.fomenko.analytics.db.entity.User;

/**
 * Created by fomenko on 10.03.2017.
 */
public interface UserDao extends GenericDao<User>{

    public boolean isExistByEmail(String email);

    public User getUserByEmail(String email);

    public User getUserAndCountSiteByUserEmail(String email);

    boolean isExistByEmailAndPassword(User user);

}
