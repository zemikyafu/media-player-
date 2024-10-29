package IntegrationTest;

import org.junit.Before;
import org.junit.Test;
import org.media_player.application.services.AuthorizationService;
import org.media_player.application.services.MediaFileService;
import org.media_player.domain.abstractions.MediaFileRepository;
import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;
import org.media_player.domain.factories.MediaFileFactoryImpl;
import org.media_player.infrastructure.in_memory_db.InMemoryMediaFileDB;
import org.media_player.infrastructure.repositories.MediaFileRepositoryImpl;

import static junit.framework.TestCase.*;

public class MediaFileServiceIntegrationTest {
    private MediaFileService mediaFileService;
    private MediaFileRepository mediaFileRepository;

    private MediaFileFactoryImpl mediaFileFactoryImpl;
    private AuthorizationService authorizationService;

    @Before
    public void setUp() {
        InMemoryMediaFileDB inMemoryMediaFileDB = new InMemoryMediaFileDB();

        mediaFileRepository = MediaFileRepositoryImpl.getInstance(inMemoryMediaFileDB);
        mediaFileFactoryImpl = new MediaFileFactoryImpl();
        authorizationService = new AuthorizationService();

        mediaFileService = new MediaFileService(mediaFileRepository, mediaFileFactoryImpl, authorizationService);
    }

    @Test
    public void testSaveMediaFile() {
        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);
        mediaFileService.saveMediaFile(loggedInUser, "video", "video1", "path", "mp4");
        MediaFile mediaFile = mediaFileService.getMediaFile("video1");
        assertTrue(mediaFile.getFileName().equals("video1"));

    }

    @Test
    public void testUpdateMediaFile() {
        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);
        mediaFileService.saveMediaFile(loggedInUser, "video", "video1", "path", "mp4");

        mediaFileService.updateMediaFile(loggedInUser, "video", "video1", "path2", "mp4");
        MediaFile mediaFile = mediaFileService.getMediaFile("video1");

        assertTrue(mediaFile.getFilePath().equals("path2"));

    }

    @Test
    public void testDeleteMediaFile() {
        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);
        mediaFileService.saveMediaFile(loggedInUser, "video", "video1", "path2", "mp4");

        mediaFileService.deleteMediaFile(loggedInUser, "video1");
        try {
            mediaFileService.getMediaFile("video1");
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Media file not found with name: video1"));
        }
    }

    @Test
    public void testGetAllMediaFiles() {
        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);
        mediaFileService.saveMediaFile(loggedInUser, "video", "video1", "path2", "mp4");
        mediaFileService.saveMediaFile(loggedInUser, "video", "video2", "path2", "mp4");

        assertEquals(2, mediaFileService.getAllMediaFiles().size());
    }

}
