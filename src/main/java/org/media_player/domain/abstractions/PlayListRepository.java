package org.media_player.domain.abstractions;

import org.media_player.domain.entities.playList.PlayList;

public interface PlayListRepository<T extends PlayList> {

    void createPlayList(T playList);

    T getPlayList(String playListName);

    void deletePlayList(T playList);

    void updatePlayList(T playList);
}
