package ua.nure.fomenko.analytics.constants;

/**
 * Created by fomenko on 10.03.2017.
 */
public class Params {


    public Params(){}

    public static final String AUTHORIZATION_ERROR = "authorizationError";

    //Request params
    public static final String SIGN_IN_ERRORS = "signInErrors";

    public static final String REGISTRATION_ERRORS = "regErrors";
    public static final String USER_CONFIRM_PASSWORD = "confirm_password";
    public static final String USER_DTO_FIELDS = "userDto";
    public static final String REQUEST_MESSAGES = "reqMessages";
    public static final String REGISTRATION_SUCCESS = "regSuccess";
    public static final String ADD_WEB_SITE_ERRORS = "webSiteErrors";
    public static final String WEB_SITE_FIELDS = "webSiteFields";
    public static final String REQUEST_SITE_LIST = "reqSiteList";
    public static final String ADD_WEB_SITE_SUCCESS = "addWebSiteSuccess";
    public static final String EDIT_WEB_SITE_SUCCESS = "editWebSiteSuccess";
    public static final String REQUEST_ERROR = "requestError";
    public static final String REQUEST_LINK_LIST = "reqLinksList";
    public static final String ADD_LINK_SUCCESS = "addLinkSuccess";
    public static final String ADD_LINK_ERROR = "addLinkError";
    public static final String LINK_FIELDS = "linkFields";


    //Session params
    public static final String SESSION_USER = "sessionUser";
    public static final String SESSION_ERRORS = "sessionErrors";
    public static final String SESSION_USER_FILL_FIELDS = "sessionUserDto";
    public static final String SESSION_MESSAGES = "sessionMessages";
    public static final String SESSION_WEB_SITE_FILL_FIELDS = "sessionWebSiteDto";
    public static final String SESSION_LINK_FILL_FIELDS = "sessionLinkDto";

    //Services
    public static final String USER_SERVICE = "userService";
    public static final String WEB_SITE_SERVICE = "webSiteService";
    public static final String LINKS_SERVICE = "linksService";
    public static final String VISITERS_SERVICE = "visitersService";
    public static final String KEY_CONVERTER_SERVICE = "keyConverterService";


    // User fields
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRSTNAME = "first_name";
    public static final String USER_LASTNAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_WEB_SITE_COUNT = "webSiteCount";

    // Website fields
    public static final String WEBSITE_URL = "url";
    public static final String WEBSITE_USER_ID = "Users_id";

    //Link fields
    public static final String LINK_URL = "url";
    public static final String LINK_NAME = "name";
    public static final String LINK_WEB_SITE_ID = "WebSites_id";

    //Visiters fields
    public static final String VISITERS_COUNTRY_CODE = "country_code";
    public static final String VISITERS_COUNTRY = "country";
    public static final String VISITERS_COUNTRY_RUS = "country_rus";
    public static final String VISITERS_REGION = "region";
    public static final String VISITERS_REGION_RUS = "region_rus";
    public static final String VISITERS_CITY = "city";
    public static final String VISITERS_CITY_RUS = "city_rus";
    public static final String VISITERS_LATITUDE = "latitude";
    public static final String VISITERS_LONGITUDE = "longitude";
    public static final String VISITERS_ZIP_CODE = "zip_code";
    public static final String VISITERS_TIME_ZONE = "time_zone";
    public static final String VISITERS_IP = "ip";

    //Statistic
    public static final String STATISTIC_VISITER_ID = "visiters_id";
    public static final String STATISTIC_LINK_ID = "links_id";

    public static final String ID = "id";



}
