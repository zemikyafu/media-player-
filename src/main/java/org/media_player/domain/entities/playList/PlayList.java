package org.media_player.domain.entities.playList;

import org.media_player.domain.entities.media.MediaFile;
import org.media_player.domain.entities.user.User;

import java.util.List;

public interface PlayList<T extends MediaFile> {

    String getName();

    User getOwner();

    void addMediaFile(T mediaFile);

    void removeMediaFile(T mediaFile);

    List<T> getMediaFiles();

}
