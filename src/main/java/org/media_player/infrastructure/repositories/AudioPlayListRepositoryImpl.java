package org.media_player.infrastructure.repositories;

import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.playList.AudioPlayList;

import org.media_player.infrastructure.in_memory_db.InMemoryAudioPlayListDB;

public class AudioPlayListRepositoryImpl implements PlayListRepository<AudioPlayList> {
    private final InMemoryAudioPlayListDB inMemoryAudioPlayListDB;

    public AudioPlayListRepositoryImpl(InMemoryAudioPlayListDB inMemoryAudioPlayListDB) {
        this.inMemoryAudioPlayListDB = inMemoryAudioPlayListDB;
    }

    @Override
    public void createPlayList(AudioPlayList playList) {
        inMemoryAudioPlayListDB.createPlayList(playList);
    }

    @Override
    public AudioPlayList getPlayList(String playListName) {
        return inMemoryAudioPlayListDB.getPlayList(playListName);

    }

    @Override
    public void deletePlayList(AudioPlayList playList) {
        inMemoryAudioPlayListDB.deletePlayList(playList);
    }

    @Override
    public void updatePlayList(AudioPlayList playList) {
        inMemoryAudioPlayListDB.updatePlayList(playList);
    }
}
