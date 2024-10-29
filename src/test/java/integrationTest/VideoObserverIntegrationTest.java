package integrationTest;

import org.junit.Before;
import org.junit.Test;
import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.observers.VideoMediaObserver;

import static org.junit.Assert.assertEquals;

public class VideoObserverIntegrationTest {
    private Video video;
    private VideoMediaObserver videoMediaObserver;

    @Before
    public void setUp() {
        video = new Video("video", "path", "mp4");
        videoMediaObserver = new VideoMediaObserver();
        video.addObserver(videoMediaObserver);
    }
    @Test
    public void testPlay() {
        video.setBrightness(40);

        assertEquals("Brightness40", videoMediaObserver.getState());
    }
}
