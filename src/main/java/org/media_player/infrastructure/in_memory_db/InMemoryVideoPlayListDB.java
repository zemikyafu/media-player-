package org.media_player.infrastructure.in_memory_db;

import org.media_player.domain.entities.media.Video;
import org.media_player.domain.entities.playList.PlayList;
import org.media_player.domain.entities.playList.VideoPlayList;

import java.util.HashMap;
import java.util.Map;

public class InMemoryVideoPlayListDB {
    private final Map<String, VideoPlayList> playLists = new HashMap<>();

    public void createPlayList(VideoPlayList playList) {
        playLists.put(playList.getName(), playList);
    }

    public VideoPlayList getPlayList(String playListName) {
        return playLists.get(playListName);
    }

    public void deletePlayList(VideoPlayList playList) {
        playLists.remove(playList.getName());
    }

    public void updatePlayList(VideoPlayList playList) {
        playLists.put(playList.getName(), playList);
    }

}
