package com.blizuk.financetracker.model;


public class User {
    private  Long id;
    private  String username;
    private  String password;
    private  UserRole role;

    public User (String username, UserRole role)
    {
        this.username = username;
        this.role = role;
    }

    public Long getUserId() { return id;}

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role)
    {
        this.role = role;
    }


    @Override
    public String toString()
    {
        return "Пользователь: { id - " + id +" }; Имя: { username - " + username + " }; Пароль: { password  - " + password + " }; Роль: { role - " + role + " }";
    }
}

