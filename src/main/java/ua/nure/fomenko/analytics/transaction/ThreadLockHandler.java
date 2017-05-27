package ua.nure.fomenko.analytics.transaction;

import java.sql.Connection;

/**
 * Created by fomenko on 10.03.2017.
 */
public class ThreadLockHandler {
    public static final ThreadLocal<Connection> CONNECTION_POOL = new ThreadLocal<>();

    public static void setConnection(Connection connection) {
        CONNECTION_POOL.set(connection);
    }

    public static Connection getConnection() {
        return CONNECTION_POOL.get();
    }
}
