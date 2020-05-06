package com.adamkoksal.WebServiceProject.Entity;

public class User {
    private int id;
    private String username;
    private String password;
    private boolean isManager;
    private boolean isApproved;

    public User(int id, String username, String password, boolean isManager, boolean isApproved) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
        this.isApproved = isApproved;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", isManager=" + isManager +
                '}';
    }
}
