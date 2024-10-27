package org.media_player.infrastructure.repositories;

import org.media_player.domain.entities.user.User;
import org.media_player.domain.abstractions.UserRepository;
import org.media_player.infrastructure.in_memory_db.InMemoryUserDB;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final InMemoryUserDB inMemoryUserDB;

    public UserRepositoryImpl(InMemoryUserDB inMemoryUserDB) {
        this.inMemoryUserDB = inMemoryUserDB;
    }

    @Override
    public void registerUser(User user) {
        inMemoryUserDB.save(user);
    }

    @Override
    public Optional<User> findByEmail(String username) {
        Optional<User> user = inMemoryUserDB.findByEmail(username);
        return user;
    }


    @Override
    public List<User> findAllUser() {
        List<User> user = inMemoryUserDB.findAllUser();
        return user;
    }

    @Override
    public void deleteUser(User user) {
        inMemoryUserDB.deleteUserById(user.getId());
    }


    @Override
    public void deleteUserByEmail(String email) {
        inMemoryUserDB.deleteUserByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        inMemoryUserDB.updateUser(user.getId(), user);
    }
}
