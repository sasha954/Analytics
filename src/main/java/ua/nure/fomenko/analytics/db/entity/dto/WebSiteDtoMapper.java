package ua.nure.fomenko.analytics.db.entity.dto;

import ua.nure.fomenko.analytics.constants.Params;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fomenko on 06.04.2017.
 */
public class WebSiteDtoMapper {
    public WebSiteDto getWebSite(HttpServletRequest request){
        WebSiteDto webSiteDto = new WebSiteDto();
        webSiteDto.setUrl(request.getParameter(Params.WEBSITE_URL));
        return webSiteDto;

    }
}
