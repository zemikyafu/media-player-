package org.media_player.infrastructure.in_memory_db;

import org.media_player.application.exceptions.UserException;
import org.media_player.domain.entities.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUserDB {
    private UUID id;
    private List<User> users = new ArrayList<>();

    private int generateId() {
        return UUID.randomUUID().hashCode();
    }

    public void save(User user) {
        int id = generateId();
        user.setId(id);
        users.add(user);
    }

    public List<User> findAllUser() {
        return users;
    }

    public Optional<User> findUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny();

    }

    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findAny();

    }

    public void deleteUserById(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void deleteUserByEmail(String email) {
        users.removeIf(users -> users.getEmail().equals(email));

    }

    public void updateUser(int id, User updatedUser) {
        Optional<User> user = users.stream().filter(users -> users.getId() == id).findAny();

        if (user.isPresent()) {
            user.get().setName(updatedUser.getName());
            user.get().setEmail(updatedUser.getEmail());
            user.get().setPassword(updatedUser.getPassword());
            user.get().setRole(updatedUser.getRole());
        } else {
            throw new UserException("User not found with id: " + id);
        }

    }

}
