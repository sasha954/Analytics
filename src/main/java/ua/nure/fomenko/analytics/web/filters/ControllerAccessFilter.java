package ua.nure.fomenko.analytics.web.filters;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.db.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by fomenko on 14.03.2017.
 */
public class ControllerAccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(ControllerAccessFilter.class);
    private List<String> outOfControll = new ArrayList<>();
    private List<String> commons = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.trace("Access filter initialization starts.");
        commons = asList(filterConfig.getInitParameter("commons"));
        LOG.debug("Common controllers ---> " + commons);

        outOfControll = asList(filterConfig.getInitParameter("out-of-control"));
        LOG.debug("Out-of-control controllers ---> " + outOfControll);

    }

    private List<String> asList(String commons) {
        List<String> list = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(commons);
        while (stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }
        return list;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(isAccessAllowed(request)) {
            LOG.trace("Access allowed");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            LOG.trace("Forbidden");
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(404);
        }
    }

    private boolean isAccessAllowed(HttpServletRequest request) {
        String requestUri = request.getRequestURI().split(request.getContextPath())[1];
        System.out.println(requestUri);
        if(outOfControll.contains(requestUri)) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if(session == null) {
            return false;
        }

        User user = (User) session.getAttribute("sessionUser");
        if(user == null) {
            return false;
        }
        return commons.contains(requestUri);
    }

    @Override
    public void destroy() {

    }
}
