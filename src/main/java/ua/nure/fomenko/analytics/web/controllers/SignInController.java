package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.MessageKeys;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.services.LoginService;
import ua.nure.fomenko.analytics.services.UserService;
import ua.nure.fomenko.analytics.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by fomenko on 14.03.2017.
 */
@WebServlet(name = "SignInController", urlPatterns = "/signIn.d")
public class SignInController extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(SignInController.class);

    private UserService userService;
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        loginService = new LoginService();
        userService = (UserService)getServletContext().getAttribute(Params.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareRequestParam(req);
        doPost(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User authUser = loginService.getUserFromRequest(req);
        Map<String, String> errors = new ValidateUtil().validateAuthorization(authUser);
        if(errors.isEmpty()) {
            if (userService.isAuthorize(authUser)) {
                authUser = userService.read(authUser.getEmail());
                req.getSession().setAttribute(Params.SESSION_USER, authUser);
                LOG.debug("Authorization SUCCESS");
                String path = Path.LINK_VIEW_CONTROLLER;
                LOG.trace("Redirect to: " + path);
                resp.sendRedirect(path);
            } else {
                LOG.debug("Authorization ERROR");
                errors.put(Params.AUTHORIZATION_ERROR, MessageKeys.WRONG_AUTHORIZATION);
                req.getSession().setAttribute(Params.SESSION_ERRORS, errors);
            }
        } else {
            LOG.debug("Authorization ERROR");
            req.getSession().setAttribute(Params.SESSION_ERRORS, errors);
        }

    }

    private void prepareRequestParam(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object errorMessages = session.getAttribute(Params.SESSION_ERRORS);
        Object sessionMessages = session.getAttribute(Params.SESSION_MESSAGES);

        req.setAttribute(Params.SIGN_IN_ERRORS, errorMessages);
        req.setAttribute(Params.REQUEST_MESSAGES, sessionMessages);

        req.removeAttribute(Params.SESSION_ERRORS);
        req.removeAttribute(Params.SESSION_MESSAGES);
    }
}
