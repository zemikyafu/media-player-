package org.media_player.domain.entities.media;

import org.media_player.domain.entities.observers.AudioMediaObserver;
import org.media_player.domain.entities.observers.MediaObserver;
import org.media_player.domain.entities.observers.MediaSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Audio implements MediaFile, MediaSubject {
    private String fileName;
    private String filePath;
    private String fileExtension;

    private String state;
    private List<MediaObserver> observers;

    public Audio(String fileName, String filePath, String fileExtension) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.observers = new ArrayList<>();
        addObserver(new AudioMediaObserver());
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
        state = "Volume:" + volume;
        notifyObservers(state);
    }

    public void setBass(int bass) {
        state = "Bass:" + bass;
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
        //stream to remove observer
        //observers.stream().filter(o->o!=observer).collect(Collectors.toList());
        observers.removeIf(o->o.equals(observer));

    }

    @Override
    public void notifyObservers(String message) {
        for (MediaObserver observer : observers) {
            observer.update(message);
        }
    }
}
