package ua.nure.fomenko.analytics.db.entity;

/**
 * Created by fomenko on 25.02.2017.
 */
public class Links extends Entity {
    private String url;
    private String name;
    private String paramForUrlOnWebSite;
    private WebSite webSite;
    private long countVisiters;

    public String getParamForUrlOnWebSite() {
        return paramForUrlOnWebSite;
    }

    public void setParamForUrlOnWebSite(String paramForUrlOnWebSite) {
        this.paramForUrlOnWebSite = paramForUrlOnWebSite;
    }

    public long getCountVisiters() {
        return countVisiters;
    }

    public void setCountVisiters(long countVisiters) {
        this.countVisiters = countVisiters;
    }

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
