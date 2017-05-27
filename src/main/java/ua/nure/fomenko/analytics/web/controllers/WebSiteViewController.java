package ua.nure.fomenko.analytics.web.controllers;

import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.services.UserService;
import ua.nure.fomenko.analytics.services.WebSiteService;
import ua.nure.fomenko.analytics.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by fomenko on 08.04.2017.
 */
@WebServlet(name = "WebSiteViewController", urlPatterns = "/siteList.d")
public class WebSiteViewController extends HttpServlet {

    private WebSiteService webSiteService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        webSiteService = (WebSiteService)getServletContext().getAttribute(Params.WEB_SITE_SERVICE);
        userService = (UserService)getServletContext().getAttribute(Params.USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepareRequestParam(request);
        String path = Path.SITE_LIST_PAGE;
        request.getRequestDispatcher(path).forward(request,response);
    }

    private void prepareRequestParam(HttpServletRequest request) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(Params.SESSION_USER);
        List<WebSite> webSiteList = webSiteService.getWebSitesByUser(user);
        int webSiteCount = userService.read(user.getEmail()).getCountSites();
        request.setAttribute(Params.REQUEST_SITE_LIST, webSiteList);
        request.setAttribute("webSiteCount", webSiteCount);
    }

}
