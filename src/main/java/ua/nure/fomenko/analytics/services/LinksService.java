package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.db.entity.dto.LinkDto;

import java.util.List;

/**
 * Created by fomenko on 05.04.2017.
 */
public interface LinksService {

    public int addLink(Links link);
    public Links getLinkByUrl(String url);
    public Links getLinkById(int id);
    public List<Links> getLinksByWebSite(WebSite webSite);
    public boolean deleteLink(Links links);
    public boolean isExistByUrl(String url);
    public  Links getLinkFromDto(LinkDto linkDto);

}
