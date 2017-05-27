package ua.nure.fomenko.analytics.db.entity.dto;

import java.util.ArrayList;

/**
 * Created by fomenko on 25.02.2017.
 */
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordRepeat;


    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("UserDTO = [").append("id='").append(id)
               .append("', mail='").append(email)
               .append("', firstName='").append(firstName)
               .append("', lastName='").append(lastName)
               .append("']");
       return stringBuilder.toString();
    }
}
