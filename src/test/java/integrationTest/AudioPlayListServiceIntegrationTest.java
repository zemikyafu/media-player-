package integrationTest;

import org.junit.Before;
import org.junit.Test;
import org.media_player.application.services.AudioPlayListService;
import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.playList.AudioPlayList;
import org.media_player.domain.entities.playList.PlayList;
import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;
import org.media_player.infrastructure.in_memory_db.InMemoryAudioPlayListDB;
import org.media_player.infrastructure.repositories.AudioPlayListRepositoryImpl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AudioPlayListServiceIntegrationTest {
    private   PlayListRepository<AudioPlayList> playListRepository;
    private AudioPlayListService audioPlayListService;


    @Before
    public void setUp() {
        InMemoryAudioPlayListDB inMemoryAudioPlayListDB = new InMemoryAudioPlayListDB();
        playListRepository= new AudioPlayListRepositoryImpl(inMemoryAudioPlayListDB);

        audioPlayListService = new AudioPlayListService(playListRepository );
    }

    @Test
    public void testSaveAudioPlayList() {
        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        audioPlayListService.createPlayList( "audio1", loggedInUser);

        PlayList audioPlayList = audioPlayListService.getPlayList("audio1");
        assertTrue(audioPlayList.getName().equals("audio1"));

    }

    @Test
    public void testAddMediaFileToPlayList() {
        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        Audio mediaFile = new Audio("audio1", "path", "mp3");

        audioPlayListService.createPlayList( "audio1", loggedInUser);
        audioPlayListService.addMediaFileToPlayList("audio1", mediaFile);

        PlayList audioPlayList = audioPlayListService.getPlayList("audio1");

        assertTrue(audioPlayList.getMediaFiles().stream().toList().size() == 1);

    }
    @Test
    public void testRemoveMediaFileFromPlayList() {
        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        Audio mediaFile = new Audio("audio1", "path", "mp3");

        audioPlayListService.createPlayList( "audio1", loggedInUser);
        audioPlayListService.addMediaFileToPlayList("audio1", mediaFile);
        audioPlayListService.removeMediaFileFromPlayList("audio1", mediaFile);

        PlayList audioPlayList = audioPlayListService.getPlayList("audio1");

        assertTrue(audioPlayList.getMediaFiles().stream().toList().size() == 0);

    }

    @Test
    public void testDeleteAudioPlayList() {
        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        audioPlayListService.createPlayList( "audio1", loggedInUser);
        audioPlayListService.deletePlayList("audio1");

        assertNull(audioPlayListService.getPlayList("audio1"));
    }

    @Test
    public void testGetMediaFilesFromPlayList() {
        User loggedInUser = new User("name", "email", "pass");
        loggedInUser.setRole(Role.ADMIN);

        Audio mediaFile = new Audio("audio1", "path", "mp3");

        audioPlayListService.createPlayList( "audio1", loggedInUser);
        audioPlayListService.addMediaFileToPlayList("audio1", mediaFile);

        assertTrue(audioPlayListService.getMediaFilesFromPlayList("audio1")
                .stream().filter(file->file.getFileName()=="audio1").toList().size() == 1);

    }
}
