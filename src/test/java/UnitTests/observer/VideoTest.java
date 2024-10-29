package UnitTests.observer;

import org.junit.Before;
import org.junit.Test;
import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.observers.VideoMediaObserver;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VideoTest {
    private Video video;

    @Mock
    private VideoMediaObserver mockVideoMediaObserver;

    @Before
    public void setUp() {
        video = new Video("video", "path", "mp4");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddObserver() {
        video.addObserver(mockVideoMediaObserver);

        video.notifyObservers("Stopped");
        verify(mockVideoMediaObserver, times(1)).update("Stopped");

    }
    @Test
    public void testNotifyObservers() {
        video.addObserver(mockVideoMediaObserver);
        video.play();

        verify(mockVideoMediaObserver, times(1)).update("Playing");
    }

    @Test
    public void testPlay() {
        video.play();
        assertEquals("Playing", video.getState());
    }

    @Test
    public void testPause() {
        video.pause();
        assertEquals("Paused", video.getState());
    }

    @Test
    public void testStop() {
        video.stop();
        assertEquals("Stopped", video.getState());
    }

}