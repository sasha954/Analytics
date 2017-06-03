package ua.nure.fomenko.analytics.web.controllers;

import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.services.LinksService;
import ua.nure.fomenko.analytics.services.WebSiteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by fomenko on 03.06.2017.
 */
@WebServlet(name = "DeleteLinkController", urlPatterns = "/deleteLink.d")
public class DeleteLinkController extends HttpServlet {

    private static final String SITE_ID = "siteId";
    private static final String LINK_ID = "linkId";

    private LinksService linksService;
    private WebSiteService webSiteService;

    @Override
    public void init() throws ServletException {
        linksService = (LinksService) getServletContext().getAttribute(Params.LINKS_SERVICE);
        webSiteService = (WebSiteService) getServletContext().getAttribute(Params.WEB_SITE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepareRequestParam(request, response);
        execute(request, response);
        String path = Path.DELETE_LINK_PAGE;
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void prepareRequestParam(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter(SITE_ID) != null) {
            int siteId = Integer.parseInt(request.getParameter(SITE_ID));
            WebSite webSite = webSiteService.getWebSiteById(siteId);
            List<Links> links = linksService.getLinksByWebSite(webSite);
            request.setAttribute(Params.REQUEST_LINK_LIST, links);
        }
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter(LINK_ID) != null) {
            int linkId = Integer.parseInt(request.getParameter(LINK_ID));
            Links link = linksService.getLinkById(linkId);
            if (link != null) {
                linksService.deleteLink(link);
            }
        }
    }


}
