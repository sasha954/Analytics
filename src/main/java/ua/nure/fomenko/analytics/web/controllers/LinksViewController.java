package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.User;
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
 * Created by fomenko on 04.05.2017.
 */
@WebServlet(name = "LinksViewController", urlPatterns = "/viewLinks.d")
public class LinksViewController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(LinksViewController.class);
    private static final String USER = "user";

    private LinksService linksService;
    private WebSiteService webSiteService;

    @Override
    public void init() throws ServletException {
        linksService = (LinksService) getServletContext().getAttribute(Params.LINKS_SERVICE);
        webSiteService = (WebSiteService) getServletContext().getAttribute(Params.WEB_SITE_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepareRequestParam(request);
        String path = Path.LINK_VIEW_PAGE;
        request.getRequestDispatcher(path).forward(request, response);
    }

    private void prepareRequestParam(HttpServletRequest request) {
        if (request.getParameter("sid") != null) {
            int webSiteId = Integer.parseInt(request.getParameter("sid"));
            WebSite webSite = webSiteService.getWebSiteById(webSiteId);
            List<Links> linksList = linksService.getLinksByWebSite(webSite);
            request.setAttribute(Params.REQUEST_LINK_LIST, linksList);
            request.setAttribute(Params.WEB_SITE_ID, webSite.getId());
        }
        User user = (User) request.getSession().getAttribute(Params.SESSION_USER);
        request.getServletContext().setAttribute(USER, user);
    }
}
