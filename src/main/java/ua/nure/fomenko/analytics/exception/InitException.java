package ua.nure.fomenko.analytics.exception;

/**
 * Created by fomenko on 10.03.2017.
 */
public class InitException extends RuntimeException{

    public InitException(){super();}

    public InitException(String message, Throwable cause){super(message, cause);}

    public InitException(String message){super(message);}
}
