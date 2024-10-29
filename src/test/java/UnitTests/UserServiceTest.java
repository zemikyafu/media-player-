package UnitTests;

import org.media_player.application.exceptions.UserException;
import org.media_player.application.services.AuthorizationService;
import org.media_player.application.services.HashService;
import org.media_player.application.services.UserService;
import org.media_player.domain.abstractions.UserRepository;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private HashService hashService;
    @Mock
    private AuthorizationService authorizationService;
    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignup() throws NoSuchAlgorithmException {
        String name = "name";
        String email = "email";
        String password = "password";

        when(hashService.hash(password)).thenReturn("hashedPassword");

        userService.signup(name, email, password);
        verify(userRepository).registerUser(argThat(user ->
                user.getName().equals(name) &&
                        user.getEmail().equals(email) &&
                        user.getPassword().equals("hashedPassword")
        ));
    }

    @Test
    public void testSignUpWithException() throws NoSuchAlgorithmException {
        User user = new User();

        String name = "name";
        String email = "email";
        String password = "password";

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        when(hashService.hash(password)).thenThrow(new NoSuchAlgorithmException());
        assertThrows(RuntimeException.class, () -> userService.signup(name, email, password));
    }

    @Test
    public void testUpdateUserAsAdmin() {
        User loggedInUser = new User();

        loggedInUser.setRole(Role.ADMIN);
        loggedInUser.setName("Admin");
        loggedInUser.setEmail("admin@email");

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(true);

        User updatedUser = new User();
        updatedUser.setName("name");
        updatedUser.setEmail("user@email");

        userService.updateUser(loggedInUser, updatedUser);
        verify(userRepository).updateUser(updatedUser);
    }

    @Test
    public void testUpdateUserAsNonAdmin() {
        User loggedInUser = new User();

        loggedInUser.setRole(Role.FREE_USER);
        loggedInUser.setName("User");
        loggedInUser.setEmail("user@email");

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(false);

        User updatedUser = new User();
        updatedUser.setName("name");
        updatedUser.setEmail("user@email");

        UserException exception = assertThrows(UserException.class, () -> userService.updateUser(loggedInUser, updatedUser));

        assertEquals("Only admin can update user", exception.getMessage());
        verify(userRepository, never()).updateUser(updatedUser);
    }

    @Test
    public void testDeleteUserAsAdmin() {
        User loggedInUser = new User();

        loggedInUser.setRole(Role.ADMIN);
        loggedInUser.setName("Admin");
        loggedInUser.setEmail("admin@email");

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(true);

        User user = new User();
        user.setName("name");
        user.setEmail("user@email");

        userService.deleteUser(loggedInUser, user);
        verify(userRepository).deleteUser(user);
    }
    @Test
    public void testDeleteUserAsnonoAdmin() {
        User loggedInUser = new User();

        loggedInUser.setRole(Role.FREE_USER);
        loggedInUser.setName("User");
        loggedInUser.setEmail("user@email");

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(false);

        User user = new User();
        user.setName("name");
        user.setEmail("user@email");

        UserException exception = assertThrows(UserException.class, () -> userService.deleteUser(loggedInUser, user));

        assertEquals("Only admin can delete user", exception.getMessage());
        verify(userRepository, never()).deleteUser(user);
    }

    @Test
    public void testFindUserByEmail() {
        String email = "email";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user));

        assertEquals(user, userService.findByEmail(email));
    }
}
