package org.media_player.domain.entities.media;

public interface MediaFile {
    String getFileName();

    String getFilePath();

    String getFileExtension();

    void play();

    void stop();

    void pause();

    void setVolume(int volume);
}
