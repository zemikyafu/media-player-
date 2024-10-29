package integrationTest;

import org.junit.Before;
import org.junit.Test;
import org.media_player.application.services.AudioPlayListService;
import org.media_player.application.services.VideoPlayListService;
import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.playList.AudioPlayList;
import org.media_player.domain.entities.playList.PlayList;
import org.media_player.domain.entities.playList.VideoPlayList;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;
import org.media_player.infrastructure.in_memory_db.InMemoryVideoPlayListDB;
import org.media_player.infrastructure.repositories.VideoPlayListRepositoryImpl;

import static org.junit.Assert.*;

public class VideoPlayListServiceIntegrationTest {
    private PlayListRepository<VideoPlayList> playListRepository;
    private VideoPlayListService videoPlayListService;

    @Before
    public void setUp() {

        InMemoryVideoPlayListDB inMemoryVideoPlayListDB = new InMemoryVideoPlayListDB();
        playListRepository = new VideoPlayListRepositoryImpl(inMemoryVideoPlayListDB);

        videoPlayListService = new VideoPlayListService(playListRepository);

    }

    @Test
    public void testSaveVideoPlayList() {

        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        videoPlayListService.createPlayList("video1", loggedInUser);

        PlayList videoPlayList = videoPlayListService.getPlayList("video1");
        assertTrue(videoPlayList.getName().equals("video1"));

    }

    @Test
    public void testAddMediaFileToPlayList() {

        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        Video mediaFile = new Video("video1", "path", "mp3");

        videoPlayListService.createPlayList("videoPlaylist1", loggedInUser);
        videoPlayListService.addMediaFileToPlayList("videoPlaylist1", mediaFile);

        PlayList videoPlayList1 = videoPlayListService.getPlayList("videoPlaylist1");

        assertTrue(videoPlayList1.getMediaFiles().stream().toList().size() == 1);

    }

    @Test
    public void testRemoveMediaFileFromPlayList() {

        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        Video mediaFile = new Video("video1", "path", "mp3");

        videoPlayListService.createPlayList("videoPlaylist1", loggedInUser);
        videoPlayListService.addMediaFileToPlayList("videoPlaylist1", mediaFile);

        PlayList videoPlayList1 = videoPlayListService.getPlayList("videoPlaylist1");

        assertTrue(videoPlayList1.getMediaFiles().stream().toList().size() == 1);

        videoPlayListService.removeMediaFileFromPlayList("videoPlaylist1", mediaFile);

        videoPlayList1 = videoPlayListService.getPlayList("videoPlaylist1");

        assertTrue(videoPlayList1.getMediaFiles().stream().toList().size() == 0);

    }

    @Test
    public void testDeleteVideoPlayList() {

        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        videoPlayListService.createPlayList("videoPlaylist1", loggedInUser);
        videoPlayListService.deletePlayList("videoPlaylist1");

        assertNull(videoPlayListService.getPlayList("videoPlaylist1"));
    }

    @Test
    public void testGetMediaFilesFromPlayList() {

        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        Video mediaFile = new Video("video1", "path", "mp3");

        videoPlayListService.createPlayList("videoPlaylist1", loggedInUser);
        videoPlayListService.addMediaFileToPlayList("videoPlaylist1", mediaFile);

        PlayList videoPlayList1 = videoPlayListService.getPlayList("videoPlaylist1");

        assertTrue(videoPlayList1.getMediaFiles().stream().toList().size() == 1);

    }
}
