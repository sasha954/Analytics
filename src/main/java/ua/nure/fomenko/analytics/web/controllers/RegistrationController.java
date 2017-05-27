package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.MessageKeys;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.db.entity.dto.UserDto;
import ua.nure.fomenko.analytics.db.entity.dto.UserDtoMapper;
import ua.nure.fomenko.analytics.services.RegistrationService;
import ua.nure.fomenko.analytics.services.UserService;
import ua.nure.fomenko.analytics.services.UserServiceImpl;
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
 * Created by fomenko on 11.03.2017.
 */
@WebServlet(name = "RegistrationController", urlPatterns = "/registration.d")
public class RegistrationController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(RegistrationController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = Path.REGISTRATION_PAGE;
        prepareRequestParam(req);
        LOG.trace("Forwarding to: " + path);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = new UserDtoMapper().getUser(req);
        Map<String,String> errors = new ValidateUtil().validateRegistration(userDto);

        if(errors.isEmpty()){
            RegistrationService registrationService = new RegistrationService();
            User registrationUser = registrationService.getRegisterUserFromDto(userDto);
            UserService userService = (UserServiceImpl)req.getServletContext().getAttribute(Params.USER_SERVICE);
            if(userService.isExistByEmail(registrationUser.getEmail())){
                errors.put(Params.USER_EMAIL, MessageKeys.USER_MAIL_ALREADY_EXIST);
                setUserDtoInSession(userDto, req);
                registrationError(errors, req, resp);
            } else {
                int userId = userService.create(registrationUser);
                if(userId != 0){
                    Map<String, String> messages = new HashMap<>();
                    messages.put(Params.REGISTRATION_SUCCESS, MessageKeys.REGISTRATION_SUCCESS);
                    req.getSession().setAttribute(Params.SESSION_MESSAGES, messages);
                }
                registrationSuccess(req, resp);
            }
        } else {
            setUserDtoInSession(userDto, req);
            registrationError(errors, req, resp);
        }
    }

    private void registrationSuccess(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = Path.INDEX_CONTROLLER;
        LOG.trace("Forwarding to: " + path);
        resp.sendRedirect(req.getServletContext().getContextPath() + path);
    }

    private void registrationError(Map<String, String> errors, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("Registration ERROR");
        req.getSession().setAttribute(Params.SESSION_ERRORS, errors);
        String path = Path.REGISTRATION_CONTREOLLER;
        LOG.trace("Forwarding to: " + path);
        resp.sendRedirect(path);
    }

    private void setUserDtoInSession(UserDto userDto, HttpServletRequest req) {
        userDto.setPassword(null);
        userDto.setPasswordRepeat(null);
        req.getSession().setAttribute(Params.SESSION_USER_FILL_FIELDS, userDto);
    }

    private void prepareRequestParam(HttpServletRequest req) {
        LOG.trace("Prepare param for page");
        HttpSession session = req.getSession();
        Object errorMap = session.getAttribute(Params.SESSION_ERRORS);
        Object userDto = session.getAttribute(Params.SESSION_USER_FILL_FIELDS);

        //set object to request
        req.setAttribute(Params.REGISTRATION_ERRORS, errorMap);
        req.setAttribute(Params.USER_DTO_FIELDS, userDto);

        //remove object from session
        req.getSession().removeAttribute(Params.SESSION_ERRORS);
        req.getSession().removeAttribute(Params.SESSION_USER_FILL_FIELDS);
        LOG.debug("Parameters was prepared");
    }
}
