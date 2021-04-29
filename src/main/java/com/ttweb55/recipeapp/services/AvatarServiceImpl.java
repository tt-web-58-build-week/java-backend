package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Avatar;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service(value = "avatarService")
public class AvatarServiceImpl implements AvatarService {


    @Override
    public Avatar save(MultipartFile avatarFile) {
        File newFile = new File("src/main/resources/avatarFile.tmp");
        System.out.println(newFile.getAbsolutePath());
        return null;
    }
}
