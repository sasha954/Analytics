package ua.nure.fomenko.analytics.db.entity;

/**
 * Created by fomenko on 25.02.2017.
 */
public class Links extends Entity {
    private String url;
    private String name;
    private WebSite webSite;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }
}
