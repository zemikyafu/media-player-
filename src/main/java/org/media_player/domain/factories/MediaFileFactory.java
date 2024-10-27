package org.media_player.domain.factories;

import org.media_player.domain.entities.media.MediaFile;

public interface MediaFileFactory {
    MediaFile createMediaFile(String mediaType, String fileName, String filePath, String fileExtension);
}
