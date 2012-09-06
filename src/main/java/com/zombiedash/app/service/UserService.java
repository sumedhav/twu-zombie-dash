package com.zombiedash.app.service;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Iterables.filter;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User authenticateAndReturnUser(String username, String password) {
        return userRepository.getUser(username, password);
    }

    public List<User> getAllUsers() {
        return userRepository.retrieveAllUsers();
    }

    public User getUser(String username){
        return userRepository.getUser(username);
    }

    public boolean createUser(User user, String password) {
        if(userRepository.userNameExists(user.getUserName())) throw new IllegalArgumentException("userNameAlreadyExists");
        return userRepository.createUser(user, password);
    }

    public void deleteUser(String username){
        if(username.equals("admin")) throw new RuntimeException("Cannot Delete Admin user.");
        userRepository.deleteUser(username);
    }

    public List<User> getAllNonAdminUsers() {
        List<User> allUsers = getAllUsers();
        Predicate<User> isNonAdmin = new Predicate<User>() {
            public boolean apply(User user) {
                return user.getRole() != Role.ADMIN;
            }
        };
        List<User> nonAdminUsers = Lists.newArrayList(filter(allUsers, isNonAdmin));
        return nonAdminUsers;
    }
}
