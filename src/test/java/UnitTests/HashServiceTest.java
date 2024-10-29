package UnitTests;

import org.junit.Test;
import org.media_player.application.services.HashService;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class HashServiceTest {
    @Test
    public void testHashService() throws NoSuchAlgorithmException {
        HashService hashService = new HashService();
        String hash = hashService.hash("password");
        assertEquals("XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=", hash);
    }
    @Test
    public void testException() {
        HashService hashService = new HashService();
        try {
            hashService.hash("password");
        } catch (NoSuchAlgorithmException e) {
            assertEquals("java.security.NoSuchAlgorithmException", e.toString());
        }
    }
}
