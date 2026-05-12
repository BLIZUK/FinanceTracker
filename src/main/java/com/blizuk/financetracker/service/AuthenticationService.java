package com.blizuk.financetracker.service;


import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.model.UserRole;
import com.blizuk.financetracker.repository.AuthenticationRepository;


public class AuthenticationService {
    private final AuthenticationRepository repository = new AuthenticationRepository();


    public void addUser(String username, String password, UserRole role)
    {
        User u = new User();
        u.setUserName(username);
        u.setPassword(password);
        u.setRole(role);

        repository.save(u);
    }

    public boolean authenticateUser(String name){ return repository.authentication(name); }
}
