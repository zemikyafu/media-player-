package org.media_player.domain.entities.playList;

import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.user.User;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayList implements PlayList<Video> {
    private String name;
    private User owner;
    private List<Video> videoMediaFiles;

    public VideoPlayList(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.videoMediaFiles = new ArrayList<>();

    }

    public String getName() {
        return this.name;
    }

    public User getOwner() {
        return this.owner;
    }

    @Override
    public void addMediaFile(Video mediaFile) {
        videoMediaFiles.add(mediaFile);
    }

    @Override
    public void removeMediaFile(Video mediaFile) {
        videoMediaFiles.remove(mediaFile);
    }

    @Override
    public List<Video> getMediaFiles() {
        return new ArrayList<>(videoMediaFiles);
    }
}
