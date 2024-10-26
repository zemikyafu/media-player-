package org.media_player.application.services;

import org.media_player.application.exceptions.InvalidPasswordException;
import org.media_player.application.exceptions.UserNotFoundException;
import org.media_player.domain.abstractions.UserRepository;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;

import java.security.NoSuchAlgorithmException;

public class UserService {
    private final UserRepository userRepository;
    private final HashService hashService;

    public UserService(UserRepository userRepository, HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;

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

    public void login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        try {
            String hashedPassword = hashService.hash(password);
            if (!user.getPassword().equals(hashedPassword)) {
                throw new InvalidPasswordException("Invalid login password");
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Hashing algorithm not found" + e.getMessage());

        }

    }

    public void updateRole(User updatedUser, Role role) {
        updatedUser.setRole(role);
        userRepository.updateUser(updatedUser);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }
}
