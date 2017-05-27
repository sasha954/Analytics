package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.db.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fomenko on 14.03.2017.
 */
public class LoginService {

    public User getUserFromRequest(HttpServletRequest request){
        User user = new User();
        user.setEmail(request.getParameter(Params.USER_EMAIL));
        user.setPassword(request.getParameter(Params.USER_PASSWORD));

        return user;
    }
}
