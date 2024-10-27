package org.media_player.infrastructure.in_memory_db;

import org.media_player.domain.entities.media.MediaFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryMediaFileDB {
    private Map<String, MediaFile> mediaFiles = new HashMap<>();

    public void save(MediaFile mediaFile) {
        mediaFiles.put(mediaFile.getFileName(), mediaFile);
    }

    public Optional<MediaFile> getMediaFile(String fileName) {
        return Optional.ofNullable(mediaFiles.get(fileName));
    }

    public void deleteMediaFile(String fileName) {
        mediaFiles.remove(fileName);
    }

    public void updateMediaFile(String fileName, MediaFile mediaFile) {
        mediaFiles.put(fileName, mediaFile);
    }

    public List<MediaFile> getAllMediaFiles() {
        return mediaFiles.values().stream().toList();
    }


}
