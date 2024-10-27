package org.media_player.presentation.controllers;

import org.media_player.application.services.MediaFileService;

public class MediaFileController {
    private final MediaFileService mediaFileService;

    public MediaFileController(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    public void saveMediaFile(String mediaType, String fileName, String filePath, String fileExtension) {
        mediaFileService.saveMediaFile(mediaType, fileName, filePath, fileExtension);
    }

    public void getMediaFile(String fileName) {
        mediaFileService.getMediaFile(fileName);
    }

    public void getAllMediaFiles() {
        mediaFileService.getAllMediaFiles();
    }

    public void deleteMediaFile(String fileName) {
        mediaFileService.deleteMediaFile(fileName);
    }

    public void updateMediaFile(String mediaType, String fileName, String filePath, String fileExtension) {
        mediaFileService.updateMediaFile(mediaType, fileName, filePath, fileExtension);
    }


}
