package UnitTests.observer;

import org.junit.Before;
import org.junit.Test;
import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.observers.AudioMediaObserver;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AudioTest {

    @Mock
    private AudioMediaObserver mockAudioMediaObserver;
    private Audio audio;

    @Before
    public void setUp() {
        audio = new Audio("audio", "path", "mp3");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterObserver() {
        audio.addObserver(mockAudioMediaObserver);

        audio.notifyObservers("Playing");
        verify(mockAudioMediaObserver, times(1)).update("Playing");

    }
    @Test
    public void testNotifyObservers() {
        audio.addObserver(mockAudioMediaObserver);
        audio.setBass(10);

        verify(mockAudioMediaObserver, times(1)).update("Bass:10");
    }

    @Test
    public void testPlay() {
        audio.play();
        assertEquals("Playing", audio.getState());
    }

    @Test
    public void testPause() {
        audio.pause();
        assertEquals("Paused", audio.getState());
    }

    @Test
    public void testStop() {
        audio.stop();
        assertEquals("Stopped", audio.getState());
    }

}
