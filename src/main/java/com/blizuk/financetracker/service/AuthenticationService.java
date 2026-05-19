package com.blizuk.financetracker.service;


import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.model.UserRole;
import com.blizuk.financetracker.repository.AuthenticationRepository;


public class AuthenticationService {
    private final AuthenticationRepository repository = new AuthenticationRepository();

    public boolean addUser(String username, String password, UserRole role)
    {
        if (!authenticateUser(username))
        {
            return false;
        }else {
            User u = new User(username, password, role);
            repository.save(u);
            return true;
        }
    }

    // Проверка на наличие логина в БД
    public boolean findUser(String name){ return repository.findUser(name); }

    public User authenticateUser(String name, String password) { return repository.}
}
