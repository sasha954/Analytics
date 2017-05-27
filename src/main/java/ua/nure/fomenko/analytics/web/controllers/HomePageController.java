package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fomenko on 13.03.2017.
 */
@WebServlet(name = "HomePageController", urlPatterns = "/index.d")
public class HomePageController extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(HomePageController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuthUser(req)) {
            String path = Path.INDEX_PAGE;
            LOG.trace("Forwarding to: " + path);
            req.getRequestDispatcher(path).forward(req, resp);
        } else
            resp.sendRedirect(getServletContext().getContextPath() + Path.SITE_LIST_CONTROLLER);

    }

    private boolean checkAuthUser(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute(Params.SESSION_USER);
        if(user != null)
            return true;
        else
            return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
