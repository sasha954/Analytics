package ua.nure.fomenko.analytics.db.entity.dto;



import ua.nure.fomenko.analytics.constants.Params;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fomenko on 25.02.2017.
 */
public class UserDtoMapper {
   public UserDto getUser(HttpServletRequest request){
       UserDto userDto = new UserDto();
       userDto.setPassword(request.getParameter(Params.USER_PASSWORD));
       userDto.setPasswordRepeat(request.getParameter(Params.USER_CONFIRM_PASSWORD));
       userDto.setEmail(request.getParameter(Params.USER_EMAIL));
       userDto.setFirstName(request.getParameter(Params.USER_FIRSTNAME));
       userDto.setLastName(request.getParameter(Params.USER_LASTNAME));

        return userDto;
   }
}
