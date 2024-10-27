package org.media_player.presentation.controllers;

import org.media_player.application.services.UserService;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;

import java.util.List;

public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUser() {
        return userService.findAllUser();
    }

    public User getUser(String email) {
        return userService.findByEmail(email);
    }

    public void addUser(String name, String email, String password) {
        userService.signup(name, email, password);

    }

    public void login(String email, String password) {
        userService.login(email, password);

    }

    public void updateUser(User user, Role role) {
        userService.updateRole(user, role);
    }

    public void deleteUser(User user) {
        userService.deleteUser(user);
    }


}
