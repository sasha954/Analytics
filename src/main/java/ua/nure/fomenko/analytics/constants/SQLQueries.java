package ua.nure.fomenko.analytics.constants;

/**
 * Created by fomenko on 10.03.2017.
 */
public class    SQLQueries {

    //user
    public static final String USER_GET_BY_ID = "SELECT * FROM users WHERE id=?";

    public static final String USER_GET_BY_EMAIL = "select * from users where email=?";

    public static final String USER_SITE_COUNT_GET_BY_EMAIL = "select u.*, count(s.id) as webSiteCount from users as u left outer join websites as s on s.Users_id = u.id and u.email=?";

    public static final String USER_CREATE_NEW = "INSERT INTO users (email, first_name, last_name, password) values(?,?,?,?)";

    public static final String USER_UPDATE = "UPDATE users SET email=?, first_name=?, last_name=?, password=? WHERE id=?";

    public static final String USER_GET_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email=? AND password=?";


    //WebSite
    public static final String WEB_SITE_GET_BY_ID = "SELECT * FROM websites WHERE id=?";

    public static final String WEB_SITE_GET_BY_URL = "SELECT * FROM websites WHERE url=?";

    public static final String WEB_SITE_CREATE_NEW = "INSERT INTO websites (url, Users_id) VALUES(?,?)";

    public static final String WEB_SITE_UPDATE = "UPDATE websites SET url=?, Users_id=? WHERE id=?";

    public static final String WEB_SITES_GET_BY_USER = "SELECT * FROM websites WHERE Users_id=?";

    public static final String WEB_SITE_DELETE_BY_ID = "DELETE FROM websites WHERE id=?";

    //Links
    public static final String LINK_GET_BY_ID = "SELECT * FROM links WHERE id=?";

    public static final String LINK_GET_BY_URL = "SELECT * FROM links WHERE url=?";

    //public static final String LINKS_GET_BY_WEBSITE = "SELECT * FROM links WHERE WebSites_id=?";

    public static final String LINKS_GET_BY_WEBSITE = "select l.*, count(s.links_id) as countVisiters from links as l left outer join statistic as s on l.id=s.links_id WHERE l.WebSites_id=? group by l.url order by l.id asc";

    public static final String LINK_CREATE_NEW = "INSERT INTO links (url, name, WebSites_id) VALUES(?,?,?)";

    public static final String LINK_DELETE_BY_ID = "DELETE FROM links WHERE id=?";

    public static final String LINK_DELETE_BY_WEB_SITE = "DELETE FROM links WHERE WebSites_id=?";

    //Visiters

    public static final String VISITER_GET_BY_ID = "SELECT * FROM visiters WHERE id=?";

    public static final String VISITERS_GET_BY_COUNTRY = "SELECT * FROM visiters WHERE country=?";

    public static final String VISITER_CREATE_NEW = "INSERT INTO visiters(country_code, country, country_rus, region, region_rus,city, city_rus, latitude, longitude, zip_code, time_zone, ip) values(?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String VISITER_GET_BY_IP = "SELECT * FROM visiters WHERE ip=?";

    //Statistic
    public static final String STATISTIC_GET_BY_ID = "SELECT * FROM statistic WHERE id=?";

    public static final String STATISTIC_CREATE_NEW = "INSERT INTO statistic(visiters_id, links_id) VALUES(?,?)";

    public static final String STATISTIC_GET_BY_LINK_ID = "SELECT v.*, count(v.id) as countVisitors from visiters as v left outer join statistic as s on s.visiters_id = v.id where s.links_id=? group by v.country";
    //public static final String STATISTIC_GET_BY_LINK_ID = "select * from statistic WHERE links_id=?";

    public static final String STATISTIC_DELETE_BY_LINK_ID = "DELETE FROM statistic WHERE links_id=?";

    private SQLQueries() {
    }
}
