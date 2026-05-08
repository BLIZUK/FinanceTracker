package com.blizuk.financetracker.model;

import javax.management.relation.Role;

public class User {
    private Long id;
    private String username;
    private String password;
    private UserRole role;


    public void setUserName(String username) {
        this.username = username;
    }

    public String getUserName() {
        return username;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }


    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }


    @Override
    public String toString()
    {
        return "Пользователь: { " + username + " } Пароль: { " + password + " } Роль: { " + role + " }";
    }
}

