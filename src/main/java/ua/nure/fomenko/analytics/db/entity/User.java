package ua.nure.fomenko.analytics.db.entity;

/**
 * Created by fomenko on 16.02.2017.
 */
public class User extends Entity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int countSites;

    public int getCountSites() {
        return countSites;
    }

    public void setCountSites(int countSites) {
        this.countSites = countSites;
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
        StringBuilder resultString = new StringBuilder();
        resultString.append("User [").append("id = ").append(getId());
        resultString.append(", mail = ").append(email);
        resultString.append(", first name = ").append(firstName);
        resultString.append(", last name = ").append(lastName);
        resultString.append("]");
        return resultString.toString();
    }
}
