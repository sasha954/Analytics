package ua.nure.fomenko.analytics.dao;

import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.db.entity.WebSite;

import java.util.List;

/**
 * Created by fomenko on 17.03.2017.
 */
public interface WebSiteDao extends GenericDao<WebSite> {

    public boolean isExistWebSiteByURL(String url);
    public WebSite getWebSiteByUrl(String url);

    public List<WebSite> getWebSiteByUser(User user);

}
