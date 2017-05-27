package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.MessageKeys;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fomenko on 18.03.2017.
 */
@WebServlet(name = "AddWebSiteController", urlPatterns = "/addWebSite.d")
public class AddWebSiteController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AddWebSiteController.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebSiteDto webSiteDto = new WebSiteDtoMapper().getWebSite(request);
        Map<String, String> errors = new ValidateUtil().validateAddWebSite(webSiteDto);

        if(errors.isEmpty()) {
            WebSiteService webSiteService = (WebSiteService)getServletContext().getAttribute(Params.WEB_SITE_SERVICE);
            WebSite webSite = webSiteService.getRegisterWebSiteFromDto(webSiteDto);
            User user = (User)request.getSession().getAttribute(Params.SESSION_USER);
            webSite.setUser(user);
            if(webSiteService.isExistByUrl(webSite.getUrl())){
                errors.put(Params.WEBSITE_URL, MessageKeys.WEB_SITE_ALREADY_EXIST);
                setWebSiteDtoInSession(webSiteDto, request);
                addWebSiteErrors(errors, request, response);
            } else {
                int webSiteId = webSiteService.addWebSite(webSite);
                if(webSiteId != 0) {
                    Map<String, String> message = new HashMap<>();
                    message.put(Params.ADD_WEB_SITE_SUCCESS, MessageKeys.ADD_WEB_SITE_SUCCESS);
                    request.getSession().setAttribute(Params.SESSION_MESSAGES, message);
                }
                addWebSiteSuccess(request, response);
            }

        } else {
            setWebSiteDtoInSession(webSiteDto, request);
            addWebSiteErrors(errors, request, response);

        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = Path.WEB_SITE_ADD_PAGE;
        prepareRequestParam(request);
        LOG.trace("Forwarding to: " + path);
        request.getRequestDispatcher(path).forward(request, response);
    }

    private void addWebSiteSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = Path.SITE_LIST_CONTROLLER;
        LOG.trace("Forwarding to: " + path);
        response.sendRedirect(request.getServletContext().getContextPath() + path);
    }

    private void addWebSiteErrors(Map<String, String> errors, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOG.debug("Add web site errors");
        request.getSession().setAttribute(Params.SESSION_ERRORS, errors);
        String path = Path.WEB_SITE_ADD_CONTROLLER;
        LOG.trace("Forwarding to: " + path);
        response.sendRedirect(path);
    }

    private void setWebSiteDtoInSession(WebSiteDto webSiteDto, HttpServletRequest request) {
        request.getSession().setAttribute(Params.SESSION_WEB_SITE_FILL_FIELDS, webSiteDto);
    }

    private void prepareRequestParam(HttpServletRequest request) {
        LOG.trace("Prepare param for page");
        HttpSession session = request.getSession();
        Object errorsMap = session.getAttribute(Params.SESSION_ERRORS);
        Object webSiteDto = session.getAttribute(Params.SESSION_WEB_SITE_FILL_FIELDS);

        request.setAttribute(Params.ADD_WEB_SITE_ERRORS, errorsMap);
        request.setAttribute(Params.WEB_SITE_FIELDS, webSiteDto);

        request.removeAttribute(Params.SESSION_ERRORS);
        request.removeAttribute(Params.SESSION_WEB_SITE_FILL_FIELDS);
        LOG.debug("Parameters was prepared");
    }

}
