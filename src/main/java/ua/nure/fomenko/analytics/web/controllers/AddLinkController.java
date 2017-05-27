package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.MessageKeys;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.db.entity.dto.LinkDto;
import ua.nure.fomenko.analytics.db.entity.dto.LinkDtoMapper;
import ua.nure.fomenko.analytics.services.LinksService;
import ua.nure.fomenko.analytics.services.WebSiteService;
import ua.nure.fomenko.analytics.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fomenko on 04.05.2017.
 */
@WebServlet(name = "AddLinkController", urlPatterns = "/addLink.d")
public class AddLinkController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AddLinkController.class);

    private LinksService linksService;
    private WebSiteService webSiteService;

    @Override
    public void init() throws ServletException {
        linksService = (LinksService) getServletContext().getAttribute(Params.LINKS_SERVICE);
        webSiteService = (WebSiteService) getServletContext().getAttribute(Params.WEB_SITE_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LinkDto linkDto = new LinkDtoMapper().getLink(request);
        Map<String, String> errors = new ValidateUtil().validateAddLink(linkDto);

        if (errors.isEmpty()) {
            int siteId = Integer.parseInt(request.getParameter("site-id"));
            WebSite webSite = webSiteService.getWebSiteById(siteId);
            Links links = linksService.getLinkFromDto(linkDto);
            links.setWebSite(webSite);
            if (linksService.isExistByUrl(links.getUrl())) {
                errors.put(Params.LINK_URL, MessageKeys.LINK_ALREADY_EXIST);
                setLinkDtoInSession(linkDto, request);
                addLinkError(errors, request, response);
            } else {
                int linkId = linksService.addLink(links);
                if (linkId != 0) {
                    Map<String, String> message = new HashMap<>();
                    message.put(Params.ADD_LINK_SUCCESS, MessageKeys.ADD_LINK_SUCCESS);
                    request.getSession().setAttribute(Params.SESSION_MESSAGES, message);
                }

                addLinkSuccess(request, response, siteId);
            }

        } else {
            setLinkDtoInSession(linkDto, request);
            addLinkError(errors, request, response);
        }
    }

    private void addLinkSuccess(HttpServletRequest request, HttpServletResponse response, int siteId) throws IOException {
        String path = Path.LINK_VIEW_CONTROLLER;
        LOG.trace("Forwarding to: /" + path);
        response.sendRedirect(path+"?sid="+siteId);

    }

    private void setLinkDtoInSession(LinkDto linkDto, HttpServletRequest request) {
        request.getSession().setAttribute(Params.SESSION_LINK_FILL_FIELDS, linkDto);
    }

    private void addLinkError(Map<String, String> errors, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOG.debug("Add link error");
        request.getSession().setAttribute(Params.SESSION_ERRORS, errors);
        String path = Path.LINK_VIEW_CONTROLLER;
        LOG.trace("Forwarding to: " + path);
        response.sendRedirect(path);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepareRequestParam(request, response);
        String path = Path.LINK_VIEW_CONTROLLER;
        response.sendRedirect(path);
    }

    private void prepareRequestParam(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Prepare request for page");
        HttpSession httpSession = request.getSession();
        Object errorsMap = httpSession.getAttribute(Params.SESSION_ERRORS);
        Object linkDto = httpSession.getAttribute(Params.SESSION_LINK_FILL_FIELDS);

        request.setAttribute(Params.ADD_LINK_ERROR, errorsMap);
        request.setAttribute(Params.LINK_FIELDS, linkDto);

        request.removeAttribute(Params.SESSION_ERRORS);
        request.removeAttribute(Params.SESSION_LINK_FILL_FIELDS);
    }


}
