package by.talstaya.crackertracker.exception;

/**
 * DaoException is used when an exception occurred in connectionPool
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class PoolConnectionException extends Exception {
    public PoolConnectionException() {
        super();
    }

    public PoolConnectionException(String message) {
        super(message);
    }

    public PoolConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolConnectionException(Throwable cause) {
        super(cause);
    }
}
