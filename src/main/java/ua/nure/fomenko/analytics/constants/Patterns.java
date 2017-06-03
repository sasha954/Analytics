package ua.nure.fomenko.analytics.constants;

import java.util.regex.Pattern;

/**
 * Created by fomenko on 11.03.2017.
 */
public class Patterns {

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{5,12})$");

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    public static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-zА-Яа-я0-9]{3,30}$");

    public static final Pattern LINK_NAME_PATTERN = Pattern.compile("^[A-Za-zА-Яа-я0-9\\ ]{3,100}$");

    //public static final Pattern URL_PATTERN = Pattern.compile("^((https?|ftp)\\:\\/\\/)?([a-z0-9]{1})((\\.[a-z0-9-])|([a-z0-9-]))*\\.([a-z]{2,6})(\\/?)$");
    public static final Pattern URL_PATTERN = Pattern.compile("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$");
}
