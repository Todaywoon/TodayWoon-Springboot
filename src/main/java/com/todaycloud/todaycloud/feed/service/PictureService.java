package com.todaycloud.todaycloud.feed.service;


import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.feed.domain.Picture;
import com.todaycloud.todaycloud.feed.domain.PictureRepository;
import com.todaycloud.todaycloud.feed.service.dto.PictureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service

public class PictureService {

    private final PictureRepository pictureRepository;

    private final Map<String, MediaType> map;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
        this.map = new HashMap<>();
        map.put("jpeg", MediaType.IMAGE_JPEG);
        map.put("jpg", MediaType.IMAGE_JPEG);
        map.put("png", MediaType.IMAGE_PNG);
    }

    public PictureDto getPicture(Long id) {
        Picture picture = pictureRepository.findById(id);
        MediaType mediaType = map.get(getExtension(picture.getUrl()));
        InputStream in;
        try {
            in = new FileInputStream(picture.getUrl());
        } catch (FileNotFoundException e) {
            throw new ResponseException(ErrorCode.NOT_EXIST_IMAGE);
        }

        return new PictureDto(new InputStreamResource(in), mediaType);

    }


    private String getExtension(String fileName) {
        int commaIdx = fileName.lastIndexOf(".") + 1;

        if (commaIdx == -1) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        String extension = fileName.substring(commaIdx);
        System.out.println(fileName);
        System.out.println(extension.equals("png"));
        System.out.println(map.containsKey("png"));
        if (!map.containsKey(extension)) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        return extension;
    }

}
