package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fomenko on 10.03.2017.
 */
@WebServlet(name = "UserViewController", urlPatterns = "/profile.d")
public class UserViewController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UserViewController.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService)getServletContext().getAttribute(Params.USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepareRequsetParams(request);
        String path = Path.PROFILE_PAGE;
        request.getRequestDispatcher(path).forward(request,response);
    }


    private void prepareRequsetParams(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute(Params.SESSION_USER);
        request.setAttribute("user", sessionUser);
    }
}
