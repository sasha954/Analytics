package ua.nure.fomenko.analytics.db.entity.dto;

/**
 * Created by fomenko on 06.04.2017.
 */
public class WebSiteDto {


    private String url;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("url = ").append(url);

        return stringBuilder.toString();
    }
}
