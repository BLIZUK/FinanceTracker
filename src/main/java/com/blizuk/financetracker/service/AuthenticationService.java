package com.blizuk.financetracker.service;


import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.model.UserRole;
import com.blizuk.financetracker.repository.AuthenticationRepository;


public class AuthenticationService {
    private final AuthenticationRepository repository = new AuthenticationRepository();


    // Добавление нового пользователя
    public boolean addUser(String username, String password, UserRole role)
    {
        if (checkUser(username))
        {
            return false;
        }else {
            User u = new User(username, role);
            u.setPassword(password);
            repository.save(u);
            return true;
        }
    }

    // Проверка существование пользователя
    public boolean checkUser(String name) { return repository.existsByUsername(name); }

    // Аутентификация пользователя
    public User authenticateUser(String username, String password) { return repository.authenticateUser(username, password); }
}
