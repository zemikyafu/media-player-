package org.media_player.application.services;

import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.entities.playList.PlayList;
import org.media_player.domain.entities.playList.VideoPlayList;
import org.media_player.domain.entities.user.User;

import java.util.List;

public interface PlayListService<T extends MediaFile> {
    void createPlayList(String playListName, User user);

    void addMediaFileToPlayList(String playListName, T mediaFile);

    void removeMediaFileFromPlayList(String playListName, T mediaFile);

    void deletePlayList(String playListName);

    PlayList<T> getPlayList(String playListName);

    List<T> getMediaFilesFromPlayList(String playListName);


}
