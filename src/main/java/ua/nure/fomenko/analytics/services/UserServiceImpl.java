package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.dao.UserDao;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.transaction.Transaction;
import ua.nure.fomenko.analytics.transaction.TransactionManager;
import ua.nure.fomenko.analytics.util.Encryptor;

import java.sql.SQLException;

/**
 * Created by fomenko on 10.03.2017.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    public User get(int id) {
        return transactionManager.execute(() -> userDao.get(id));
    }

    @Override
    public int create(User user) {
        return transactionManager.execute(() -> {
            user.setPassword(new Encryptor().encryptPassword(user.getPassword()));
            return userDao.create(user);
        });
    }

    @Override
    public boolean isExistByEmail(String email) {
        return transactionManager.execute(() -> userDao.isExistByEmail(email));
    }

    @Override
    public boolean update(User user) {
        return transactionManager.execute(() -> userDao.update(user));
    }

    @Override
    public boolean isAuthorize(User user) {
        return transactionManager.execute(() -> {
            user.setPassword(new Encryptor().encryptPassword(user.getPassword()));
            return userDao.isExistByEmailAndPassword(user);
        });
    }

    @Override
    public User read(String email) {
        return transactionManager.execute(() -> userDao.getUserByEmail(email));
    }

    @Override
    public User readByEmail(String email) {
        return transactionManager.execute(() -> userDao.getUserAndCountSiteByUserEmail(email));
    }
}
