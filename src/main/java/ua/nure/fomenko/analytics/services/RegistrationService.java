package ua.nure.fomenko.analytics.services;

import ua.nure.fomenko.analytics.db.entity.User;
import ua.nure.fomenko.analytics.db.entity.dto.UserDto;

/**
 * Created by fomenko on 11.03.2017.
 */
public class RegistrationService {
    public User getRegisterUserFromDto(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
