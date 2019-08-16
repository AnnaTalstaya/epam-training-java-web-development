package by.talstaya.crackertracker.exception;

/**
 * DaoException is used when an exception occurred in service
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
