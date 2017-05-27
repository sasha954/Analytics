package ua.nure.fomenko.analytics.util;

import org.apache.log4j.Logger;
import ua.nure.fomenko.analytics.constants.MessageKeys;
import ua.nure.fomenko.analytics.constants.Messages;
import ua.nure.fomenko.analytics.constants.Params;
import ua.nure.fomenko.analytics.constants.Patterns;
import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.db.entity.dto.LinkDto;
import ua.nure.fomenko.analytics.db.entity.dto.UserDto;
import ua.nure.fomenko.analytics.db.entity.dto.WebSiteDto;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by fomenko on 11.03.2017.
 */
public class ValidateUtil {
    private static final Logger LOG = Logger.getLogger(ValidateUtil.class);

    public boolean isValidateByRegex(String value, Pattern pattern){
        if (value != null) {
            return pattern.matcher(value).find();
        } else {
            return false;
        }
    }

    public Map<String, String> validateRegistration(UserDto user){
        Map<String, String> errors = new HashMap<>();

        if(!isValidateByRegex(user.getEmail(), Patterns.EMAIL_PATTERN)) {
            errors.put(Params.USER_EMAIL, MessageKeys.INVALID_MAIL);
        }

        if(!isValidateByRegex(user.getPassword(), Patterns.PASSWORD_PATTERN)){
            errors.put(Params.USER_PASSWORD, MessageKeys.INVALID_PASSWORD);
        } else {
            if (!user.getPassword().equals(user.getPasswordRepeat())){
                errors.put(Params.USER_CONFIRM_PASSWORD, MessageKeys.NOT_EQUALS_PASSWORDS);
            }
        }

        if(!isValidateByRegex(user.getFirstName(), Patterns.NAME_PATTERN)){
            errors.put(Params.USER_FIRSTNAME, MessageKeys.INVALID_FIRST_NAME);
        }

        if(!isValidateByRegex(user.getLastName(), Patterns.NAME_PATTERN)) {
            errors.put(Params.USER_LASTNAME, MessageKeys.INVALID_LAST_NAME);
        }

        return errors;
     }

    public Map<String,String> validateAuthorization(User user) {
        Map<String, String> errors = new HashMap<>();
        if(!isValidateByRegex(user.getEmail(), Patterns.EMAIL_PATTERN)){
            errors.put(Params.USER_EMAIL, MessageKeys.INVALID_MAIL);
        }
        if(!isValidateByRegex(user.getPassword(), Patterns.PASSWORD_PATTERN)) {
            errors.put(Params.USER_PASSWORD, MessageKeys.INVALID_PASSWORD);
        }
        return errors;
    }

    public Map<String, String> validateAddWebSite(WebSiteDto webSite) {
        Map<String, String>errors = new HashMap<>();
        if(!isValidateByRegex(webSite.getUrl(), Patterns.URL_PATTERN)){
            errors.put(Params.WEBSITE_URL, MessageKeys.INVALID_URL);
        }
        return errors;
    }

    public Map<String,String> validateAddLink(LinkDto linkDto){
        Map<String, String> errors = new HashMap<>();
        if(!isValidateByRegex(linkDto.getUrl(), Patterns.URL_PATTERN)){
            errors.put(Params.LINK_URL, MessageKeys.INVALID_URL);
        }
        if(!isValidateByRegex(linkDto.getName(), Patterns.NAME_PATTERN)){
            errors.put(Params.LINK_NAME, MessageKeys.INVALID_NAME);
        }

        return errors;
    }

}
