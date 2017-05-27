package ua.nure.fomenko.analytics.web.controllers;

import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.services.WebSiteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fomenko on 17.04.2017.
 */
@WebServlet(name = "DeleteWebSiteController", urlPatterns = "/deleteSite.d")
public class DeleteWebSiteController extends HttpServlet {

    private WebSiteService webSiteService;

    @Override
    public void init() throws ServletException {
        webSiteService = (WebSiteService)getServletContext().getAttribute(Params.WEB_SITE_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int webSiteId = Integer.parseInt(request.getParameter("siteId"));
        String path = getServletContext().getContextPath() + Path.SITE_LIST_CONTROLLER;
        WebSite webSite = webSiteService.getWebSiteById(webSiteId);
        if(webSite != null) {
            webSiteService.deleteWebSite(webSite);
        }
        response.sendRedirect(path);
    }

}
