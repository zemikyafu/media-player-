package UnitTests;

import org.junit.Before;
import org.junit.Test;
import org.media_player.application.exceptions.MediaFileException;
import org.media_player.application.exceptions.PlayListException;
import org.media_player.application.services.AudioPlayListService;
import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.playList.AudioPlayList;
import org.media_player.domain.entities.user.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AudioPlayListServiceTest {
    @Mock
    private PlayListRepository<AudioPlayList> playListRepository;
    @InjectMocks
    private AudioPlayListService audioPlayListService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePlayListSuccess() {
        User loggedInUser = new User();

        when(playListRepository.getPlayList("playlist1")).thenReturn(null);

        audioPlayListService.createPlayList("playlist1", loggedInUser);
        verify(playListRepository).createPlayList(any(AudioPlayList.class));
    }

    @Test
    public void testCreatePlayListFailure() {
        User loggedInUser = new User();

        when(playListRepository.getPlayList("playlist1")).thenReturn(new AudioPlayList("playlist1", loggedInUser));

        assertThrows(PlayListException.class, () -> audioPlayListService.createPlayList("playlist1", loggedInUser));
        verify(playListRepository, never()).createPlayList(any(AudioPlayList.class));
    }

    @Test
    public void testAddMediaFileToPlayList() {
        User loggedInUser = new User();
        AudioPlayList playList = new AudioPlayList("playlist1", loggedInUser);
        Audio audio = new Audio("audio", "path", "mp3");

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        audioPlayListService.addMediaFileToPlayList("playlist1", audio);
        verify(playListRepository).updatePlayList(playList);
    }

    @Test
    public void testRemoveMediaFileFromPlayList() {
        User loggedInUser = new User();
        AudioPlayList playList = new AudioPlayList("playlist1", loggedInUser);
        Audio audio = new Audio("audio", "path", "mp3");

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        audioPlayListService.removeMediaFileFromPlayList("playlist1", audio);
        verify(playListRepository).updatePlayList(playList);
    }

    @Test
    public void testDeletePlayList() {
        User loggedInUser = new User();
        AudioPlayList playList = new AudioPlayList("playlist1", loggedInUser);

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        audioPlayListService.deletePlayList("playlist1");
        verify(playListRepository).deletePlayList(playList);
    }

    @Test
    public void testGetPlayList() {
        User loggedInUser = new User();
        AudioPlayList playList = new AudioPlayList("playlist1", loggedInUser);

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        assertEquals(playList, audioPlayListService.getPlayList("playlist1"));
    }

    @Test
    public void testGetMediaFilesFromPlayList() {
        User loggedInUser = new User();
        AudioPlayList playList = new AudioPlayList("playlist1", loggedInUser);

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);
        assertEquals(playList.getMediaFiles(), audioPlayListService.getMediaFilesFromPlayList("playlist1"));
    }


}
