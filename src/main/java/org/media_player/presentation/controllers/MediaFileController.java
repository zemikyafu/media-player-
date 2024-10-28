package org.media_player.presentation.controllers;

import org.media_player.application.services.MediaFileService;
import org.media_player.domain.entities.user.User;

public class MediaFileController {
    private final MediaFileService mediaFileService;

    public MediaFileController(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    public void saveMediaFile(User user, String mediaType, String fileName, String filePath, String fileExtension) {
        mediaFileService.saveMediaFile(user, mediaType, fileName, filePath, fileExtension);
    }

    public void getMediaFile(String fileName) {
        mediaFileService.getMediaFile(fileName);
    }

    public void getAllMediaFiles() {
        mediaFileService.getAllMediaFiles();
    }

    public void deleteMediaFile(User user, String fileName) {
        mediaFileService.deleteMediaFile(user,fileName);
    }

    public void updateMediaFile(User user, String mediaType, String fileName, String filePath, String fileExtension) {
        mediaFileService.updateMediaFile(user, mediaType, fileName, filePath, fileExtension);
    }


}
