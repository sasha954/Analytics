package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fomenko on 05.04.2017.
 */

@WebServlet(name = "AddVisiterController", urlPatterns = "/addVisiter.d")
public class AddVisiterController extends HttpServlet {

   private static final Logger LOG = Logger.getLogger(AddVisiterController.class);

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getParameter("ip");
        int linkId = Integer.parseInt(request.getParameter("linkId")),
                siteId = Integer.parseInt(request.getParameter("siteId"));

        LOG.debug("ip = " + ip + ", link id = " + linkId + ", site id = " + siteId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
