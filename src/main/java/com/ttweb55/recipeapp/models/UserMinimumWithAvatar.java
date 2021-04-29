package com.ttweb55.recipeapp.models;

import org.springframework.web.multipart.MultipartFile;

public class UserMinimumWithAvatar extends UserMinimum {
    private MultipartFile avatar;

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
