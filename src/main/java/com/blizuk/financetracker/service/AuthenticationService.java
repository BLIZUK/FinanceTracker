package com.blizuk.financetracker.service;


import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.model.UserRole;
import com.blizuk.financetracker.repository.AuthenticationRepository;


public class AuthenticationService {
    private final AuthenticationRepository repository = new AuthenticationRepository();

    public void register(String username, String password, UserRole role)
    {
        User u = new User(username, password, role);
        repository.save(u);
    }

    // Проверка на наличие логина в БД
    public boolean authenticateUser(String name){ return repository.authentication(name); }
}
