package ua.nure.fomenko.analytics.dao;

import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.WebSite;

import java.util.List;

/**
 * Created by fomenko on 20.03.2017.
 */
public interface LinksDao extends GenericDao<Links> {

    public boolean isExistLinkByUrl(String url);

    public Links getLinkByUrl(String url);

    public List<Links> getLinksByWebSite(WebSite webSite);
}
