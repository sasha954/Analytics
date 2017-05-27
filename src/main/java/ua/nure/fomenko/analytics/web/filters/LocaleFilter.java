package ua.nure.fomenko.analytics.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by fomenko on 13.03.2017.
 */
public class LocaleFilter implements Filter {
    private List<String> defaultLocale;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultLocale = new ArrayList<>();
        String[] tempArr = filterConfig.getInitParameter("locales").split(" ");
        defaultLocale.addAll(Arrays.asList(tempArr));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        Locale locale;
        if(lang != null && !lang.isEmpty()) {
            if(defaultLocale.contains(lang)){
                lang = defaultLocale.get(0);
            }
            locale = new Locale(lang);
            session.setAttribute("locale", locale);
        } else {
            locale = (Locale) session.getAttribute("locale");
            if(locale == null) {
                Enumeration<Locale> localeEnumeration = request.getLocales();
                locale = takeLocale(localeEnumeration);
                session.setAttribute("locale", locale);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Locale takeLocale(Enumeration<Locale> localeEnumeration) {
        Locale result = new Locale(defaultLocale.get(0));
        while (localeEnumeration.hasMoreElements()){
            Locale temp = localeEnumeration.nextElement();
            if(defaultLocale.contains(temp.getLanguage())){
                result = temp;
                break;
            }
        }
        return result;
    }

    @Override
    public void destroy() {

    }
}
