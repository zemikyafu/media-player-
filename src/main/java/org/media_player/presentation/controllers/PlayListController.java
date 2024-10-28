package org.media_player.presentation.controllers;

import org.media_player.application.services.AudioPlayListService;
import org.media_player.application.services.VideoPlayListService;
import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.user.User;

public class PlayListController {
    private final VideoPlayListService videoPlayListService;
    private final AudioPlayListService audioPlayListService;

    public PlayListController(VideoPlayListService videoPlayListService, AudioPlayListService audioPlayListService) {
        this.videoPlayListService = videoPlayListService;
        this.audioPlayListService = audioPlayListService;
    }

    public void createAudioPlayList(String playListName, User user) {

        audioPlayListService.createPlayList(playListName, user);
    }

    public void deleteAudioPlayList(String playListName) {
        audioPlayListService.deletePlayList(playListName);
    }

    public void addAudioMediaFileToPlayList(String playListName, Audio mediaFile) {
        audioPlayListService.addMediaFileToPlayList(playListName, mediaFile);
    }

    public void removeAudioMediaFileFromPlayList(String playListName, Audio mediaFile) {
        audioPlayListService.removeMediaFileFromPlayList(playListName, mediaFile);
    }

    public void createVideoPlayList(String playListName, User user) {
        videoPlayListService.createPlayList(playListName, user);
    }

    public void deleteVideoPlayList(String playListName) {
        videoPlayListService.deletePlayList(playListName);
    }

    public void addVideoMediaFileToPlayList(String playListName, Video mediaFile) {
        videoPlayListService.addMediaFileToPlayList(playListName, mediaFile);
    }

    public void removeVideoMediaFileFromPlayList(String playListName, Video mediaFile) {
        videoPlayListService.removeMediaFileFromPlayList(playListName, mediaFile);
    }

}
