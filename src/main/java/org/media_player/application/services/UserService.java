package org.media_player.application.services;

import org.media_player.application.exceptions.InvalidPasswordException;
import org.media_player.application.exceptions.UserException;
import org.media_player.domain.abstractions.UserRepository;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private final HashService hashService;
    private final AuthorizationService authorizationService;

    public UserService(UserRepository userRepository, HashService hashService, AuthorizationService authorizationService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
        this.authorizationService = authorizationService;
    }

    public void signup(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        try {
            user.setPassword(hashService.hash(password));
            userRepository.registerUser(user);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User loggedInUser, User updatedUser) {
        if (!authorizationService.isAdmin(loggedInUser)) {
            throw new UserException("Only admin can update user");
        }
        userRepository.updateUser(updatedUser);
    }

    public void deleteUser(User loggedInUser, User user) {
        if (!authorizationService.isAdmin(loggedInUser)) {
            throw new UserException("Only admin can delete user");
        }
        userRepository.deleteUser(user);
    }

    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    public User findByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return userRepository.findByEmail(email).get();
        } else {
            throw new UserException("User not found with email: " + email);
        }
    }
}
