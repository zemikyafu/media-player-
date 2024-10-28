package org.media_player.application.services;

import org.media_player.application.exceptions.MediaFileNotFoundException;
import org.media_player.application.exceptions.PlayListNotFoundException;
import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.playList.AudioPlayList;
import org.media_player.domain.entities.playList.PlayList;
import org.media_player.domain.entities.playList.VideoPlayList;
import org.media_player.domain.entities.user.User;

import java.util.List;

public class VideoPlayListService implements PlayListService<Video> {
    private final PlayListRepository<VideoPlayList> playListRepository;

    public VideoPlayListService(PlayListRepository<VideoPlayList> playListRepository) {
        this.playListRepository = playListRepository;
    }

    @Override
    public void createPlayList(String playListName, User user) {
        playListRepository.createPlayList(new VideoPlayList(playListName, user));
    }

    @Override
    public void addMediaFileToPlayList(String playListName, Video mediaFile) {
        VideoPlayList playList = playListRepository.getPlayList(playListName);
        if (playList == null) {
            throw new PlayListNotFoundException("PlayList not found");
        } else if (mediaFile == null) {
            throw new MediaFileNotFoundException("Video not found");
        } else if (playList.getMediaFiles().contains(mediaFile)) {
            throw new IllegalArgumentException("Video already exists in the playlist");
        }
        playList.addMediaFile(mediaFile);
        playListRepository.updatePlayList(playList);
    }

    @Override
    public void removeMediaFileFromPlayList(String playListName, Video mediaFile) {
        VideoPlayList playList = playListRepository.getPlayList(playListName);
        if (playList == null) {
            throw new PlayListNotFoundException("PlayList not found");
        } else if (mediaFile == null) {
            throw new MediaFileNotFoundException("Video not found");
        } else if (!playList.getMediaFiles().contains(mediaFile)) {
            throw new IllegalArgumentException("Video does not exist in the playlist");
        }
        playList.removeMediaFile(mediaFile);
    }

    @Override
    public void deletePlayList(String playListName) {
        VideoPlayList playList = playListRepository.getPlayList(playListName);
        if (playList == null) {
            throw new PlayListNotFoundException("PlayList not found");
        }
        playListRepository.deletePlayList(playList);
    }

    @Override
    public PlayList<Video> getPlayList(String playListName) {
        return playListRepository.getPlayList(playListName);
    }

    @Override
    public List<Video> getMediaFilesFromPlayList(String playListName) {
        PlayList<Video> playList = playListRepository.getPlayList(playListName);
        if (playList == null) {
            throw new PlayListNotFoundException("PlayList not found");
        }
        return playList.getMediaFiles();
    }
}
