package org.media_player.application.exceptions;

public class PlayListNotFoundException extends RuntimeException {
    public PlayListNotFoundException(String message) {
        super(message);
    }
}
