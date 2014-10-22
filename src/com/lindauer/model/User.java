package com.lindauer.model;

/**
 * @author clindauer
 * @since 10/20/14
 */
public class User {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    public User (int id, String user, String pwd, String first, String last) {
        this.userID = id;
        this.userName = user;
        this.password = pwd;
        this.firstName = first;
        this.lastName = last;
    }

    public int getId() {
        return userID;
    }

    private int userID;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userID=" + userID +
                '}';
    }
}
