package org.media_player.infrastructure.repositories;


import org.media_player.domain.abstractions.PlayListRepository;
import org.media_player.domain.entities.playList.VideoPlayList;
import org.media_player.infrastructure.in_memory_db.InMemoryVideoPlayListDB;

public class VideoPlayListRepositoryImpl implements PlayListRepository<VideoPlayList> {
    private final InMemoryVideoPlayListDB inMemoryVideoPlayListDB;

    public VideoPlayListRepositoryImpl(InMemoryVideoPlayListDB inMemoryVideoPlayListDB) {
        this.inMemoryVideoPlayListDB = inMemoryVideoPlayListDB;
    }


    @Override
    public void createPlayList(VideoPlayList playList) {
        inMemoryVideoPlayListDB.createPlayList(playList);
    }

    @Override
    public VideoPlayList getPlayList(String playListName) {
        return inMemoryVideoPlayListDB.getPlayList(playListName);
    }

    @Override
    public void deletePlayList(VideoPlayList playList) {
        inMemoryVideoPlayListDB.deletePlayList(playList);
    }

    @Override
    public void updatePlayList(VideoPlayList playList) {
        inMemoryVideoPlayListDB.updatePlayList(playList);
    }
}
