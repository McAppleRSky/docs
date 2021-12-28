package ru.mrs.docs._2_service;

public class UserProfile {

    private final String login;
    private final String pass;
    private final String email;

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public UserProfile(String commonName) {
        this.login = commonName;
        this.pass = commonName;
        this.email = commonName;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

}
