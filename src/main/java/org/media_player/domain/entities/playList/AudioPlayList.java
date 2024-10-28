package org.media_player.domain.entities.playList;

import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.entities.user.User;

import java.util.ArrayList;
import java.util.List;

public class AudioPlayList implements PlayList<Audio> {
    private String name;
    private User owner;
    private List<Audio> audioMediaFiles;

    public AudioPlayList(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.audioMediaFiles = new ArrayList<>();

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public User getOwner() {
        return null;
    }

    @Override
    public void addMediaFile(Audio mediaFile) {
        audioMediaFiles.add(mediaFile);
    }

    @Override
    public void removeMediaFile(Audio mediaFile) {
        audioMediaFiles.remove(mediaFile);
    }


    @Override
    public List<Audio> getMediaFiles() {
        return new ArrayList<>(audioMediaFiles);
    }


}
