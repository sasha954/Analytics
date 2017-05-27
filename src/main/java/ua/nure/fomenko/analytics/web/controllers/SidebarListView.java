package ua.nure.fomenko.analytics.web.controllers;

import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.services.UserService;
import ua.nure.fomenko.analytics.services.WebSiteService;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by fomenko on 02.05.2017.
 */
@WebServlet(name = "SidebarListViewController", urlPatterns = "/sidebarList.d")
public class SidebarListView extends HttpServlet {

    private WebSiteService webSiteService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        webSiteService = (WebSiteService)getServletContext().getAttribute(Params.WEB_SITE_SERVICE);
        userService = (UserService)getServletContext().getAttribute(Params.USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepareRequestParam(request);
    }

    private void prepareRequestParam(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Params.SESSION_USER);
        List<WebSite> webSiteList = webSiteService.getWebSitesByUser(user);
        int webSiteCountSites = userService.readByEmail(user.getEmail()).getCountSites();
        request.getServletContext().setAttribute(Params.REQUEST_SITE_LIST, webSiteList);
        request.getServletContext().setAttribute("webSitesCount", webSiteCountSites);
    }

}
