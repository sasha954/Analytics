package ua.nure.fomenko.analytics.db.entity;

/**
 * Created by fomenko on 25.02.2017.
 */
public class WebSite extends Entity {

    private String url;
    private User user;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
