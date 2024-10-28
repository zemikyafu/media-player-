package org.media_player.infrastructure.in_memory_db;

import org.media_player.domain.entities.media.Audio;
import org.media_player.domain.entities.playList.AudioPlayList;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAudioPlayListDB {
    private final Map<String, AudioPlayList> playLists = new HashMap<>();

    public void createPlayList(AudioPlayList playList) {
        playLists.put(playList.getName(), playList);
    }

    public AudioPlayList getPlayList(String playListName) {
        return playLists.get(playListName);
    }

    public void deletePlayList(AudioPlayList playList) {
        playLists.remove(playList.getName());
    }

    public void updatePlayList(AudioPlayList playList) {
        playLists.put(playList.getName(), playList);
    }
}
