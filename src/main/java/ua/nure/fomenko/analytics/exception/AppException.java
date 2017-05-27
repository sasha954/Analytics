package ua.nure.fomenko.analytics.exception;

/**
 * Created by fomenko on 10.03.2017.
 */
public class AppException extends RuntimeException{

    public AppException(){super();}

    public AppException(String message, Throwable cause) {super(message, cause);}

    public AppException(String message) {super(message);}
}
