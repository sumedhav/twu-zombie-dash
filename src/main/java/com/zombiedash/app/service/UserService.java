package com.zombiedash.app.service;

import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User authenticateAndReturnUser(String username, String password) {
        User admin = userRepository.retrieveAdminUser();
        if(admin.authenticate(username, password)) return admin;
        return null;
    }
}
