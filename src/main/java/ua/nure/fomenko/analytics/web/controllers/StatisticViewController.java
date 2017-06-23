package ua.nure.fomenko.analytics.web.controllers;

import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.Bean.Statistic;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.Visiters;
import ua.nure.fomenko.analytics.services.LinksService;
import ua.nure.fomenko.analytics.services.StatisticService;
import ua.nure.fomenko.analytics.services.VisitersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by fomenko on 20.06.2017.
 */
@WebServlet(name = "StatisticViewController", urlPatterns = "/statistic.d")
public class StatisticViewController extends HttpServlet {
    public static final String LINK_ID = "linkId";
    public static final String STATISTICS = "statistics";
    public static final String LINK = "link";
    public static final String VISITERS_STAT = "visitersStat";
    private StatisticService statisticService;
    private LinksService linksService;
    private VisitersService visitersService;

    @Override
    public void init() throws ServletException {
        statisticService = (StatisticService)getServletContext().getAttribute(Params.STATISTIC_SERVICE);
        linksService = (LinksService) getServletContext().getAttribute(Params.LINKS_SERVICE);
        visitersService = (VisitersService) getServletContext().getAttribute(Params.VISITERS_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareRequestParam(req);
        String path = Path.STATISTIC_PAGE;
        req.getRequestDispatcher(path).forward(req, resp);
    }

    private void prepareRequestParam(HttpServletRequest req) {
        if(req.getParameter(LINK_ID) != null) {
            int linkId = Integer.parseInt(req.getParameter(LINK_ID));
            List<Statistic> statisticList = statisticService.getStatisticListByLinkId(linkId);
            Links link = linksService.getLinkById(linkId);
           // List<Visiters> visiters = visitersService.getVisitersByStatistics(statisticList);
           // req.setAttribute(VISITERS_STAT, visiters);
            req.setAttribute(STATISTICS, statisticList);
            req.setAttribute(LINK, link);
        }
    }
}
