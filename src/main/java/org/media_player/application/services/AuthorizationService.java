package org.media_player.application.services;

import org.media_player.domain.entities.user.Role;
import org.media_player.domain.entities.user.User;

public class AuthorizationService {
    public boolean isAdmin(User user){
        return user.getRole().equals(Role.ADMIN);
    }
}
