package ua.nure.fomenko.analytics.db.entity.dto;

/**
 * Created by fomenko on 04.05.2017.
 */
public class LinkDto {
    private String id;
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Link[").append("url: ").append(url)
                .append(", name: ").append(name).append("]");
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
