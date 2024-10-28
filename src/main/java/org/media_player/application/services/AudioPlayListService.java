package org.media_player.application.services;

import org.media_player.application.exceptions.MediaFileNotFoundException;
import org.media_player.application.exceptions.PlayListNotFoundException;
import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.playList.AudioPlayList;
import org.media_player.domain.entities.playList.PlayList;
import org.media_player.domain.entities.user.User;

import java.util.List;

public class AudioPlayListService implements PlayListService<Audio> {
    private final PlayListRepository<AudioPlayList> playListRepository;

    public AudioPlayListService(PlayListRepository<AudioPlayList> playListRepository) {
        this.playListRepository = playListRepository;
    }

    @Override
    public void createPlayList(String playListName, User user) {
        playListRepository.createPlayList(new AudioPlayList(playListName, user));
    }

    @Override
    public void addMediaFileToPlayList(String playListName, Audio mediaFile) {
        AudioPlayList playList = playListRepository.getPlayList(playListName);
        if (playList == null) {
            throw new PlayListNotFoundException("PlayList not found");
        } else if (mediaFile == null) {
            throw new MediaFileNotFoundException("Audio not found");
        } else if (playList.getMediaFiles().contains(mediaFile)) {
            throw new IllegalArgumentException("Audio already exists in the playlist");
        }
        playList.addMediaFile(mediaFile);
        playListRepository.updatePlayList(playList);
    }

    @Override
    public void removeMediaFileFromPlayList(String playListName, Audio mediaFile) {
        AudioPlayList playList = playListRepository.getPlayList(playListName);
        if (playList == null) {
            throw new PlayListNotFoundException("PlayList not found");
        } else if (mediaFile == null) {
            throw new MediaFileNotFoundException("Audio not found");
        } else if (!playList.getMediaFiles().contains(mediaFile)) {
            throw new IllegalArgumentException("Audio does not exist in the playlist");
        }
        playList.removeMediaFile(mediaFile);
        playListRepository.updatePlayList(playList);
    }

    @Override
    public void deletePlayList(String playListName) {
        AudioPlayList playList = playListRepository.getPlayList(playListName);
        if (playList == null) {
            throw new PlayListNotFoundException("PlayList not found");
        }
        playListRepository.deletePlayList(playList);
    }

    @Override
    public PlayList<Audio> getPlayList(String playListName) {
        return playListRepository.getPlayList(playListName);
    }

    @Override
    public List<Audio> getMediaFilesFromPlayList(String playListName) {
        AudioPlayList playList = playListRepository.getPlayList(playListName);
        if (playList == null) {
            throw new PlayListNotFoundException("PlayList not found");
        }
        return playList.getMediaFiles();
    }
}
