package ua.nure.fomenko.analytics.db.entity.dto;

import ua.nure.fomenko.analytics.constants.Params;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;

/**
 * Created by fomenko on 04.05.2017.
 */
public class LinkDtoMapper {
    public LinkDto getLink(HttpServletRequest request){
        LinkDto linkDto = new LinkDto();
        linkDto.setUrl(request.getParameter(Params.LINK_URL));
        linkDto.setName(request.getParameter(Params.LINK_NAME));

        return linkDto;
    }
}
