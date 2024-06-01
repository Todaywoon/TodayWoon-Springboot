package com.todaycloud.todaycloud.feed.service;

import org.springframework.web.multipart.MultipartFile;

public interface PictureCreateService {
    String savePicture(MultipartFile file);
}
