package org.media_player.domain.factories;

import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.entities.media.Video;

public class MediaFileFactoryImpl implements MediaFileFactory {
    @Override
    public MediaFile createMediaFile(String mediaType, String fileName, String filePath, String fileExtension) {
        switch (mediaType.toLowerCase()) {
            case "audio":
                return new Audio(fileName, filePath, fileExtension);
            case "video":
                return new Video(fileName, filePath, fileExtension);
            default:
                throw new IllegalArgumentException("Invalid media type: " + mediaType);
        }
    }
}
