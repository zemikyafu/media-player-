package UnitTests;

import org.media_player.application.exceptions.MediaFileException;
import org.media_player.application.services.AuthorizationService;
import org.media_player.application.services.MediaFileService;
import org.media_player.domain.abstractions.MediaFileRepository;
import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;
import org.media_player.domain.factories.MediaFileFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MediaFileServiceTest {
    @Mock
    private MediaFileRepository mediaFileRepository;
    @Mock
    private MediaFileFactoryImpl mediaFileFactoryImpl;
    @Mock
    private AuthorizationService authorizationService;
    @InjectMocks
    private MediaFileService mediaFileService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMediaFileAsAmin() {

        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);

        MediaFile mediaFile = new Video("video", "path", "mp4");

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(true);
        when(mediaFileFactoryImpl.createMediaFile("video", "video1", "path", "mp4")).thenReturn(mediaFile);

        mediaFileService.saveMediaFile(loggedInUser, "video", "video1", "path", "mp4");

        verify(mediaFileRepository).saveMediaFile(mediaFile);

    }

    @Test
    public void testSaveMediaFileAsUser() {

        User loggedInUser = new User();
        loggedInUser.setRole(Role.FREE_USER);

        MediaFile mediaFile = new Audio("audio", "path", "mp3");

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(false);
        assertThrows(MediaFileException.class, () -> mediaFileService.saveMediaFile(loggedInUser, "audio", "audio1", "path", "mp3"));

    }
    @Test
    public void testGetMediaFile() {
        MediaFile mediaFile = new Video("video", "path", "mp4");

        when(mediaFileRepository.getMediaFile("video")).thenReturn(java.util.Optional.of(mediaFile));

        assertEquals(mediaFile, mediaFileService.getMediaFile("video"));
    }

    @Test
    public void testGetMediaFileNotFound() {
        when(mediaFileRepository.getMediaFile("video")).thenReturn(java.util.Optional.empty());

        assertThrows(MediaFileException.class, () -> mediaFileService.getMediaFile("video"));
    }

    @Test
    public void testDeleteMediaFileAsAdmin() {
        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(true);

        mediaFileService.deleteMediaFile(loggedInUser, "video");

        verify(mediaFileRepository).deleteMediaFile("video");
    }

    @Test
    public void testDeleteMediaFileAsUser() {
        User loggedInUser = new User();
        loggedInUser.setRole(Role.FREE_USER);

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(false);

        assertThrows(MediaFileException.class, () -> mediaFileService.deleteMediaFile(loggedInUser, "video"));
    }
    @Test
    public void testUpdateMediaFileAsAdmin() {
        User loggedInUser = new User();
        loggedInUser.setRole(Role.ADMIN);

        MediaFile mediaFile = new Audio("video", "path", "mp4");

        when(authorizationService.isAdmin(loggedInUser)).thenReturn(true);
        when(mediaFileFactoryImpl.createMediaFile("video", "video1", "path", "mp4")).thenReturn(mediaFile);

        mediaFileService.updateMediaFile(loggedInUser, "video", "video1", "path", "mp4");

        verify(mediaFileRepository).updateMediaFile(mediaFile);
    }

}
