package integrationTest;

import org.junit.Before;
import org.junit.Test;
import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.observers.AudioMediaObserver;

import static org.junit.Assert.assertEquals;

public class AudioObserverIntegrationTest {
    private Audio audio;
    private AudioMediaObserver audioMediaObserver;

    @Before
    public void setUp() {
        audio = new Audio("audio", "path", "mp3");
        audioMediaObserver = new AudioMediaObserver();
        audio.addObserver(audioMediaObserver);
    }

    @Test
    public void testPlay() {
        audio.play();

        assertEquals("Playing", audioMediaObserver.getState());
    }

}
