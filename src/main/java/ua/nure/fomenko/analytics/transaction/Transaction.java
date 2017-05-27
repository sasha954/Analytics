package ua.nure.fomenko.analytics.transaction;

import java.sql.SQLException;

/**
 * Created by fomenko on 10.03.2017.
 */
public interface Transaction<T> {
    T execute() throws SQLException;
}
