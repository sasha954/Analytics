package ua.nure.fomenko.analytics.exception;

/**
 * Created by fomenko on 10.03.2017.
 */
public class DBException extends RuntimeException{

    public DBException(){super();}

    public DBException(String message, Throwable cause){super(message, cause);}

    public DBException(String message){super(message);}
}
