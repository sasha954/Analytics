package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.db.entity.User;

/**
 * Created by fomenko on 10.03.2017.
 */
public interface UserService {

    public User get(int id);

    public int create(User user);

    public boolean isExistByEmail(String email);

    public boolean update(User user);

    public boolean isAuthorize(User user);

    public User read(String email);

    public User readByEmail(String email);


}
