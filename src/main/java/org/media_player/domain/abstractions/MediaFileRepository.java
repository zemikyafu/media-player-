package org.media_player.domain.abstractions;

import org.media_player.domain.entities.media.MediaFile;

import java.util.List;
import java.util.Optional;

public interface MediaFileRepository {

    void saveMediaFile(MediaFile mediaFile);

    Optional<MediaFile> getMediaFile(String fileName);

    List<MediaFile> getAllMediaFiles();

    void deleteMediaFile(String fileName);

    void updateMediaFile(MediaFile mediaFile);

}
