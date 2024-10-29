package org.media_player.application.dto;

import org.media_player.domain.entities.user.Role;

public class UserDto {
    private int id;
    private String name;
    private String email;
    private Role role;

    public UserDto(int id,String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
