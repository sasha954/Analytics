package ua.nure.fomenko.analytics.web.controllers;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.Visiters;
import ua.nure.fomenko.analytics.services.*;

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

    private VisiterDataRecieverService visiterDataRecieverService;
    private VisitersService visitersService;
    private StatisticService statisticService;
    private KeyConverterService keyConverterService;
    private LinksService linksService;

    @Override
    public void init() throws ServletException {
        visiterDataRecieverService = new VisiterDataRecieverService();
        visitersService = (VisitersService) getServletContext().getAttribute(Params.VISITERS_SERVICE);
        statisticService = (StatisticService) getServletContext().getAttribute(Params.STATISTIC_SERVICE);
        keyConverterService = (KeyConverterService) getServletContext().getAttribute(Params.KEY_CONVERTER_SERVICE);
        linksService = (LinksService) getServletContext().getAttribute(Params.LINKS_SERVICE);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getParameter("ip");
        int linkId = keyConverterService.keyToId(request.getParameter("linkId"));
        Links link = linksService.getLinkById(linkId);
        Visiters visiters = new Visiters();
        int visiterId = 0;
        if (!visitersService.isExistByIp(ip)) {
            visiters = visiterDataRecieverService.getVisiterDataFromAPI(ip);
            visiterId = visitersService.addVisiter(visiters);
            visiters.setId(visiterId);
            if (visiterId != 0) {
                addStatistic(link, visiters);
            }
        } else {
            visiters = visitersService.getVisiterByIp(ip);
            addStatistic(link, visiters);
        }
    }

    private void addStatistic(Links link, Visiters visiters) {
        Statistic statistic = new Statistic();
        statistic.setVisiters(visiters);
        statistic.setLinks(link);
        statisticService.createStatictic(statistic);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
