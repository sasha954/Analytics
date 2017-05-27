package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by fomenko on 14.03.2017.
 */
@WebServlet(name = "LogoutController", urlPatterns = "/logOut.d")
public class LogoutController extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(LogoutController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        LOG.debug("Session was invalidated");
        String path = getServletContext().getContextPath() + Path.INDEX_CONTROLLER;
        LOG.trace("Redirect to: " + path);
        resp.sendRedirect(path);
    }

}
