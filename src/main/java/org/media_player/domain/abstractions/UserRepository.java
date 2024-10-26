package org.media_player.domain.abstractions;

import org.media_player.domain.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void registerUser(User user);

    Optional<User> findByEmail(String email);

    List<User> findAllUser();

    void deleteUser(User user);

    void deleteUserByEmail(String email);

    void updateUser(User user);


}
