package org.media_player.domain.entities.media;

import org.media_player.domain.entities.observers.AudioMediaObserver;
import org.media_player.domain.entities.observers.MediaObserver;
import org.media_player.domain.entities.observers.MediaSubject;
import org.media_player.domain.entities.observers.VideoMediaObserver;

import java.util.ArrayList;
import java.util.List;

public class Video implements MediaFile, MediaSubject {
    private String fileName;
    private String filePath;
    private String fileExtension;
    private List<MediaObserver> observers;
    private String state;

    public Video(String fileName, String filePath, String fileExtension) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.observers = new ArrayList<>();
        addObserver(new VideoMediaObserver());

    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public String getFileExtension() {
        return fileExtension;
    }

    @Override
    public void play() {
        state = "Playing";
        notifyObservers(state);
    }

    @Override
    public void stop() {
        state = "Stopped";
        notifyObservers(state);
    }

    @Override
    public void pause() {
        state = "Paused";
        notifyObservers(state);
    }

    @Override
    public void setVolume(int volume) {
        state = "Volume" + volume;
        notifyObservers(state);
    }

    public void setBrightness(int brightness) {
        state = "Brightness" + brightness;
        notifyObservers(state);
    }

    public List<MediaObserver> getObservers() {
        return observers;
    }

    public String getState() {
        return state;
    }

    @Override
    public void addObserver(MediaObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MediaObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (MediaObserver observer : observers) {
            observer.update(message);
        }
    }
}
