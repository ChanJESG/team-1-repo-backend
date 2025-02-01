package com.example.rootsquad.backend.dto;

import com.example.rootsquad.backend.model.EnumRole;

public class UserDto {
    private String name;
    private String email;
    private String password;
    private String userBio;
    private String profilePicUrl;
    private EnumRole role;
    public UserDto() {
    }

    public UserDto(String name, String email, String password, EnumRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDto(String name, String email, String password, String userBio, String profilePicUrl, EnumRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userBio = userBio;
        this.profilePicUrl = profilePicUrl;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }
}
