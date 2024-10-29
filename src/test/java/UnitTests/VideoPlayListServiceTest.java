package UnitTests;

import org.junit.Before;
import org.junit.Test;
import org.media_player.application.exceptions.PlayListException;
import org.media_player.application.services.VideoPlayListService;
import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.playList.VideoPlayList;
import org.media_player.domain.entities.user.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VideoPlayListServiceTest {
    @Mock
    private PlayListRepository<VideoPlayList> playListRepository;
    @InjectMocks
    private VideoPlayListService videoPlayListService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePlayListSuccess() {
        User loggedInUser = new User();

        when(playListRepository.getPlayList("playlist1")).thenReturn(null);

        videoPlayListService.createPlayList("playlist1", loggedInUser);
        verify(playListRepository).createPlayList(any(VideoPlayList.class));
    }

    @Test
    public void testCreatePlayListFailure() {
        User loggedInUser = new User();

        when(playListRepository.getPlayList("playlist1")).thenReturn(new VideoPlayList("playlist1", loggedInUser));

        assertThrows(PlayListException.class, () -> videoPlayListService.createPlayList("playlist1", loggedInUser));
        verify(playListRepository, never()).createPlayList(any(VideoPlayList.class));
    }

    @Test
    public void testAddMediaFileToPlayList() {
        User loggedInUser = new User();
        VideoPlayList playList = new VideoPlayList("playlist1", loggedInUser);
        Video video = new Video("video", "path", "mp4");

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        videoPlayListService.addMediaFileToPlayList("playlist1", video);
        verify(playListRepository).updatePlayList(playList);
    }

    @Test
    public void testGetMediaFilesFromPlayListFailure() {
        User loggedInUser = new User();

        when(playListRepository.getPlayList("playlist1")).thenReturn(null);

        assertThrows(PlayListException.class, () -> videoPlayListService.getMediaFilesFromPlayList("playlist1"));
    }

    @Test
    public void testRemoveMediaFileFromPlayList() {
        User loggedInUser = new User();
        VideoPlayList playList = new VideoPlayList("playlist1", loggedInUser);
        Video video = new Video("video", "path", "mp4");

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        videoPlayListService.removeMediaFileFromPlayList("playlist1", video);
        verify(playListRepository).updatePlayList(playList);
    }

    @Test
    public void testDeletePlayList() {
        User loggedInUser = new User();
        VideoPlayList playList = new VideoPlayList("playlist1", loggedInUser);

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        videoPlayListService.deletePlayList("playlist1");
        verify(playListRepository).deletePlayList(playList);
    }

    @Test
    public void testGetPlayList() {
        User loggedInUser = new User();
        VideoPlayList playList = new VideoPlayList("playlist1", loggedInUser);

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        assertEquals(playList, videoPlayListService.getPlayList("playlist1"));
    }

    @Test
    public void testGetMediaFilesFromPlayList() {
        User loggedInUser = new User();
        VideoPlayList playList = new VideoPlayList("playlist1", loggedInUser);

        when(playListRepository.getPlayList("playlist1")).thenReturn(playList);

        assertEquals(playList.getMediaFiles(), videoPlayListService.getMediaFilesFromPlayList("playlist1"));
    }


    @Test
    public void testAddMediaFileToPlayListFailure() {
        User loggedInUser = new User();
        Video video = new Video("video", "path", "mp4");

        when(playListRepository.getPlayList("playlist1")).thenReturn(null);

        assertThrows(PlayListException.class, () -> videoPlayListService.addMediaFileToPlayList("playlist1", video));
        verify(playListRepository, never()).updatePlayList(any(VideoPlayList.class));
    }

    @Test
    public void testRemoveMediaFileFromPlayListFailure() {
        User loggedInUser = new User();
        Video video = new Video("video", "path", "mp4");

        when(playListRepository.getPlayList("playlist1")).thenReturn(null);

        assertThrows(PlayListException.class, () -> videoPlayListService.removeMediaFileFromPlayList("playlist1", video));
        verify(playListRepository, never()).updatePlayList(any(VideoPlayList.class));
    }

    @Test
    public void testDeletePlayListFailure() {
        User loggedInUser = new User();

        when(playListRepository.getPlayList("playlist1")).thenReturn(null);

        assertThrows(PlayListException.class, () -> videoPlayListService.deletePlayList("playlist1"));
        verify(playListRepository, never()).deletePlayList(any(VideoPlayList.class));
    }

}
