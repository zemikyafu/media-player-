package org.media_player.domain.entities.observers;

public class VideoMediaObserver implements MediaObserver{
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void update(String state) {
        this.state = state;
        System.out.println("Video state: " + state);
    }
}
