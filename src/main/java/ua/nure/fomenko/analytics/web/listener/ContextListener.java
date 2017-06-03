package ua.nure.fomenko.analytics.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.dao.*;
import ua.nure.fomenko.analytics.dao.impl.*;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.exception.InitException;
import ua.nure.fomenko.analytics.services.*;
import ua.nure.fomenko.analytics.transaction.TransactionManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;

/**
 * Created by fomenko on 10.03.2017.
 */
@WebListener
public class ContextListener implements ServletContextListener{
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        log4jInit(servletContext);

        DataSource dataSource;
        try {
            Context dataSourceContext = new InitialContext();
            dataSource = (DataSource) dataSourceContext.lookup("java:comp/env/jdbc/analytics");
        } catch (NamingException e){
            LOG.error(Messages.ERR_DATA_SOURCE_INIT, e);
            throw new InitException(Messages.ERR_DATA_SOURCE_INIT, e);
        }

        TransactionManager transactionManager = new TransactionManager(dataSource);

        UserDao userDao = new UserDaoImpl();
        WebSiteDao webSiteDao = new WebSiteDaoImpl();
        LinksDao linksDao = new LinksDaoImpl();
        VisitersDao visitersDao = new VisitersDaoImpl();
        StatisticDao statisticDao = new StatisticDaoImpl();

        UserService userService = new UserServiceImpl(userDao, transactionManager);
        WebSiteService webSiteService = new WebSiteServiceImpl(webSiteDao, transactionManager);
        LinksService linksService = new LinksServiceImpl(linksDao, transactionManager);
        VisitersService visitersService = new VisitersServiceImpl(visitersDao, transactionManager);
        KeyConverterService keyConverterService = new DefaultKeyConverterServiceImpl();
        StatisticService statisticService = new StatisticServiceImpl(statisticDao, transactionManager);

        servletContext.setAttribute(Params.USER_SERVICE, userService);
        servletContext.setAttribute(Params.WEB_SITE_SERVICE, webSiteService);
        servletContext.setAttribute(Params.LINKS_SERVICE, linksService);
        servletContext.setAttribute(Params.VISITERS_SERVICE, visitersService);
        servletContext.setAttribute(Params.KEY_CONVERTER_SERVICE, keyConverterService);
        servletContext.setAttribute(Params.STATISTIC_SERVICE, statisticService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("Servlet context destruction starts.");
        //Nothing to do
        log("Servlet context destruction finished");
    }

    private void log4jInit(ServletContext servletContext) {
        log("Log4j initialization starts.");
        try {
            String homeDir = servletContext.getRealPath("/");
            File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
            PropertyConfigurator.configure(propertiesFile.toString());
            LOG.info(Messages.LOG4J_INIT);
        } catch (Throwable e){
            log(Messages.ERR_LOG4J_INIT);
            throw new InitException(Messages.ERR_LOG4J_INIT, e);
        }
    }

    private void log(String message) {
        System.out.println("CONTEXT LISTENER MESSAGE: " + message);
    }

}
