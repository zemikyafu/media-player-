package org.media_player.application.exceptions;

public class MediaFileNotFoundException extends RuntimeException {
    public MediaFileNotFoundException(String message) {
        super(message);
    }
}
