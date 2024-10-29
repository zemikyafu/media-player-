package UnitTests;

import org.junit.Test;
import org.media_player.application.services.AuthorizationService;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;

import static org.junit.Assert.assertTrue;

public class AuthorizationServiceTest {

    @Test
    public void testAuthorizationService() {

        AuthorizationService authorizationService = new AuthorizationService();
        User user = new User();
        user.setRole(Role.ADMIN);
        assertTrue(authorizationService.isAdmin(user));
    }

}
