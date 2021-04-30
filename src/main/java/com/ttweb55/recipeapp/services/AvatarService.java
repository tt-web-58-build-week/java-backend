package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Avatar;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {
    Avatar save(MultipartFile avatarFile);
}
