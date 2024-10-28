package org.media_player.application.services;

import org.media_player.application.exceptions.MediaFileException;
import org.media_player.domain.abstractions.MediaFileRepository;
import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.entities.user.User;
import org.media_player.domain.factories.MediaFileFactoryImpl;

import java.util.List;

public class MediaFileService {
    private final MediaFileRepository mediaFileRepository;
    private final MediaFileFactoryImpl mediaFileFactoryImpl;
    private final AuthorizationService authorizationService;

    public MediaFileService(MediaFileRepository mediaFileRepository, MediaFileFactoryImpl mediaFileFactoryImpl, AuthorizationService authorizationService) {
        this.mediaFileRepository = mediaFileRepository;
        this.mediaFileFactoryImpl = mediaFileFactoryImpl;
        this.authorizationService = authorizationService;
    }

    public void saveMediaFile(User user, String mediaType, String fileName, String filePath, String fileExtension) {
        if(!authorizationService.isAdmin(user)){
            throw new MediaFileException("User is not authorized to save media file");
        }
        MediaFile mediaFile = mediaFileFactoryImpl.createMediaFile(mediaType, fileName, filePath, fileExtension);
        mediaFileRepository.saveMediaFile(mediaFile);

    }

    public MediaFile getMediaFile(String fileName) {
        if (mediaFileRepository.getMediaFile(fileName).isPresent()) {
            return mediaFileRepository.getMediaFile(fileName).get();
        } else {
            throw new MediaFileException("Media file not found with name: " + fileName);
        }
    }

    public void deleteMediaFile(User user, String fileName) {
        if(!authorizationService.isAdmin(user)){
            throw new MediaFileException("User is not authorized to delete media file");
        }
        mediaFileRepository.deleteMediaFile(fileName);
    }

    public List<MediaFile> getAllMediaFiles() {
        return mediaFileRepository.getAllMediaFiles();
    }

    public void updateMediaFile(User user,String mediaType, String fileName, String filePath, String fileExtension) {
        if(!authorizationService.isAdmin(user)){
            throw new MediaFileException("User is not authorized to update media file");
        }
        MediaFile mediaFile = mediaFileFactoryImpl.createMediaFile(mediaType, fileName, filePath, fileExtension);
        mediaFileRepository.updateMediaFile(mediaFile);
    }

}
