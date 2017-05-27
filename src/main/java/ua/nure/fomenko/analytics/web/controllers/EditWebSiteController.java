package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.WebSite;
import ua.nure.fomenko.analytics.db.entity.dto.WebSiteDto;
import ua.nure.fomenko.analytics.db.entity.dto.WebSiteDtoMapper;
import ua.nure.fomenko.analytics.services.WebSiteService;
import ua.nure.fomenko.analytics.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fomenko on 17.04.2017.
 */
@WebServlet(name = "EditWebSiteController", urlPatterns = "/editWebSite.d")
public class EditWebSiteController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditWebSiteController.class);
    private WebSiteService webSiteService;
    private WebSite webSite;

    @Override
    public void init() throws ServletException {
        webSiteService = (WebSiteService)getServletContext().getAttribute(Params.WEB_SITE_SERVICE);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebSiteDto webSiteDto = new WebSiteDtoMapper().getWebSite(request);
        Map<String, String> error = new ValidateUtil().validateAddWebSite(webSiteDto);

        if(error.isEmpty()) {
            if(webSite != null) {
                webSite.setUrl(request.getParameter(Params.WEBSITE_URL));
                boolean result = webSiteService.updateWebSite(webSite);
                if(result){
                    Map<String, String> message = new HashMap<>();
                    message.put(Params.EDIT_WEB_SITE_SUCCESS, Params.EDIT_WEB_SITE_SUCCESS);
                    request.setAttribute(Params.REQUEST_MESSAGES, message);
                }
                editWebSiteSuccess(request, response);
            }

        }else  {
            editWebSiteError(error, request, response);
        }
    }

    private void editWebSiteError(Map<String, String> error, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOG.debug("Edit web site errors");
        request.getSession().setAttribute(Params.REQUEST_ERROR, error);
        String path = Path.WEB_SITE_EDIT_CONTROLLER;
        LOG.trace("Forwarding to: " + path);
        response.sendRedirect(path);
    }


    private void editWebSiteSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = Path.SITE_LIST_CONTROLLER;
        LOG.trace("Forwarding to: " + path);
        response.sendRedirect(request.getServletContext().getContextPath() + path);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = Path.WEB_SITE_EDIT_PAGE;
        prepareRequestParam(request);
        request.getRequestDispatcher(path).forward(request,response);
    }

    private void prepareRequestParam(HttpServletRequest request){
        int siteId = Integer.parseInt(request.getParameter("siteId"));
        webSite = webSiteService.getWebSiteById(siteId);
        request.setAttribute("webSite", webSite);
    }
}
