package org.media_player.domain.entities.observers;

public interface MediaSubject {
    void addObserver(MediaObserver observer);
    void removeObserver(MediaObserver observer);
    void notifyObservers(String message);
}
