package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.db.entity.dto.WebSiteDto;

import java.util.List;

/**
 * Created by fomenko on 18.03.2017.
 */
public interface WebSiteService {

    public int addWebSite(WebSite webSite);

    public WebSite getWebSiteByUrl(String url);

    public WebSite getWebSiteById(int id);

    public List<WebSite> getWebSitesByUser(User user);

    public boolean updateWebSite(WebSite webSite);

    public boolean deleteWebSite(WebSite webSite);

    public boolean isExistByUrl(String url);

    public WebSite getRegisterWebSiteFromDto(WebSiteDto webSiteDto);


}
