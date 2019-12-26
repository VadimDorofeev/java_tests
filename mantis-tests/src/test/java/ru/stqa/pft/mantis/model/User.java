package ru.stqa.pft.mantis.model;

public class User {
    private String email;
    private String username;

    public User(String username, String email) {
        this.username  = username;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
