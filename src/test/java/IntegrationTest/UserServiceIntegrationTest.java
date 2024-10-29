package IntegrationTest;

import org.junit.Before;
import org.junit.Test;
import org.media_player.application.services.AuthorizationService;
import org.media_player.application.services.HashService;
import org.media_player.application.services.UserService;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;
import org.media_player.infrastructure.in_memory_db.InMemoryUserDB;
import org.media_player.infrastructure.repositories.UserRepositoryImpl;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class UserServiceIntegrationTest {
    private UserService userService;
    private UserRepositoryImpl userRepository;
    private AuthorizationService authorizationService;
    private HashService hashService;

    @Before
    public void setUp() {
        InMemoryUserDB inMemoryUserDB = new InMemoryUserDB();
        userRepository = UserRepositoryImpl.getInstance(inMemoryUserDB);

        authorizationService= new AuthorizationService();
        hashService = new HashService();

        userService = new UserService(userRepository, hashService, authorizationService);
    }

    @Test
    public void testRegisterUser() {
        userService.signup("name", "test@email", "password");
        Optional<User> userFound = userRepository.findByEmail("test@email");
        assertTrue(userFound.isPresent());

    }
    @Test
    public void testUpdateUserASaAdmin() {
        userService.signup("name", "test@email", "password");
        Optional<User> userFound = userRepository.findByEmail("test@email");

        assertTrue(userFound.isPresent());

        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);

        User user = userFound.get();
        user.setName("name2");

        userService.updateUser(loggedInUser, user);

        Optional<User> updatedUser = userRepository.findByEmail("test@email");

        assertTrue(updatedUser.isPresent());
        assertTrue(updatedUser.get().getName().equals("name2"));
    }

    @Test
    public void testDeleteUserAsAdmin() {
        userService.signup("name", "test@email", "password");
        Optional<User> userFound = userRepository.findByEmail("test@email");

        assertTrue(userFound.isPresent());

        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);

        User user = userFound.get();
        userService.deleteUser(loggedInUser, user);

        Optional<User> deletedUser = userRepository.findByEmail("test@email");

        assertTrue(deletedUser.isEmpty());
    }
}