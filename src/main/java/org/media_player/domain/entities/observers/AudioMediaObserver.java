package org.media_player.domain.entities.observers;

import org.media_player.domain.entities.media.Audio;

public class AudioMediaObserver implements MediaObserver{
    String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void update(String state) {
        this.state = state;
        System.out.println("Audio state: " + state);
    }
}
