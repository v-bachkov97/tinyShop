package com.example.tinyshop.model.view;

public class AdminPanelUserView {

    private long id;
    private String fullName;
    private String role;

    public AdminPanelUserView(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
