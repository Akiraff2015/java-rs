package com.akiraff.user;

public class Session {
    private String username = null;
    private String role = null;
    private boolean login = false;

    public Session(Username user) {
        this.username = user.getUsername();
        this.role = user.getRole();
        this.login = true;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getLogin() {
        return this.login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public void end() {
        this.username = null;
        this.role = null;
        this.login = false;
    }
}