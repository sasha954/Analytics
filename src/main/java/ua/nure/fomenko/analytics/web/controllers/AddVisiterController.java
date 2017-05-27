package ua.nure.fomenko.analytics.web.controllers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.services.VisitersService;

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

    private HttpClient httpClient;
    private HttpPost httpPost;
    private VisitersService visitersService;
    private HttpResponse response;
    private String requestTo2ip = "http://api.2ip.ua/geo.json?ip=";

    @Override
    public void init() throws ServletException {
        httpClient = new DefaultHttpClient();
        visitersService = (VisitersService)getServletContext().getAttribute(Params.VISITERS_SERVICE);
        httpPost = new HttpPost(requestTo2ip);
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
