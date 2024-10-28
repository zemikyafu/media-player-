package org.media_player.infrastructure.repositories;

import org.media_player.domain.abstractions.MediaFileRepository;
import org.media_player.domain.entities.media.MediaFile;
import org.media_player.infrastructure.in_memory_db.InMemoryMediaFileDB;

import java.util.List;
import java.util.Optional;

public class MediaFileRepositoryImpl implements MediaFileRepository {

    private final InMemoryMediaFileDB inMemoryMediaFileDB;
    private static MediaFileRepositoryImpl instance;

    private MediaFileRepositoryImpl(InMemoryMediaFileDB inMemoryMediaFileDB) {
        this.inMemoryMediaFileDB = inMemoryMediaFileDB;
    }
    public static synchronized MediaFileRepositoryImpl getInstance(InMemoryMediaFileDB inMemoryMediaFileDB) {
        if (instance == null) {
            instance = new MediaFileRepositoryImpl(inMemoryMediaFileDB);
        }
        return instance;
    }

    @Override
    public void saveMediaFile(MediaFile mediaFile) {
        inMemoryMediaFileDB.save(mediaFile);
    }

    @Override
    public Optional<MediaFile> getMediaFile(String fileName) {
        return inMemoryMediaFileDB.getMediaFile(fileName);
    }

    @Override
    public List<MediaFile> getAllMediaFiles() {
        return inMemoryMediaFileDB.getAllMediaFiles();
    }

    @Override
    public void deleteMediaFile(String fileName) {
        inMemoryMediaFileDB.deleteMediaFile(fileName);
    }

    @Override
    public void updateMediaFile(MediaFile mediaFile) {
        inMemoryMediaFileDB.updateMediaFile(mediaFile.getFileName(), mediaFile);
    }
}
