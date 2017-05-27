package ua.nure.fomenko.analytics.transaction;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.exception.AppException;
import ua.nure.fomenko.analytics.exception.DBException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by fomenko on 10.03.2017.
 */
public class TransactionManager {
    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T execute(Transaction<T> transaction) {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            ThreadLockHandler.setConnection(connection);
            T value = transaction.execute();
            connection.commit();
            LOG.info(Messages.TRANSACTION_SUCCESS);
            return value;
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_TRANSACTION, e);
            throw new AppException(Messages.ERR_TRANSACTION, e);
        } finally {
            ThreadLockHandler.setConnection(null);
            close(connection);
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                LOG.info(Messages.CONNECTION_CLOSED);
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, e);
                throw new DBException(Messages.ERR_CANNOT_CLOSE_CONNECTION, e);
            }
        }
    }

    private void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
                LOG.info(Messages.ROLLBACK_SUCCESS);
            } catch (SQLException e){
                LOG.error(Messages.ERR_CANNOT_EXECUTE_ROLLBACK, e);
                throw new DBException(Messages.ERR_CANNOT_EXECUTE_ROLLBACK, e);
            }
        }
    }


}
