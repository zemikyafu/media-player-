package org.media_player.application.services;

import org.media_player.application.exceptions.MediaFileNotFoundException;
import org.media_player.domain.abstractions.MediaFileRepository;
import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.factories.MediaFileFactoryImpl;

import java.util.List;
import java.util.Optional;

public class MediaFileService {
    private final MediaFileRepository mediaFileRepository;
    private final MediaFileFactoryImpl mediaFileFactoryImpl;

    public MediaFileService(MediaFileRepository mediaFileRepository, MediaFileFactoryImpl mediaFileFactoryImpl) {
        this.mediaFileRepository = mediaFileRepository;
        this.mediaFileFactoryImpl = mediaFileFactoryImpl;
    }

    public void saveMediaFile(String mediaType, String fileName, String filePath, String fileExtension) {
        MediaFile mediaFile = mediaFileFactoryImpl.createMediaFile(mediaType, fileName, filePath, fileExtension);
        mediaFileRepository.saveMediaFile(mediaFile);

    }

    public MediaFile getMediaFile(String fileName) {
        if (mediaFileRepository.getMediaFile(fileName).isPresent()) {
            return mediaFileRepository.getMediaFile(fileName).get();
        } else {
            throw new MediaFileNotFoundException("Media file not found with name: " + fileName);
        }
    }

    public void deleteMediaFile(String fileName) {
        mediaFileRepository.deleteMediaFile(fileName);
    }

    public List<MediaFile> getAllMediaFiles() {
        return mediaFileRepository.getAllMediaFiles();
    }

    public void updateMediaFile(String mediaType, String fileName, String filePath, String fileExtension) {
        MediaFile mediaFile = mediaFileFactoryImpl.createMediaFile(mediaType, fileName, filePath, fileExtension);
        mediaFileRepository.updateMediaFile(mediaFile);
    }

}
