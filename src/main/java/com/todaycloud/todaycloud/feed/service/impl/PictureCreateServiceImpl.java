package com.todaycloud.todaycloud.feed.service.impl;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.feed.service.PictureCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PictureCreateServiceImpl implements PictureCreateService {

    //TODO 아래 2개 properties 에 넣도록
    private static final String SAVE_PATH = "/Users/yongha/picture";
    private static final Set<String> VALID_EXTENSIONS = Set.of("jpeg", "jpg", "png");

    @Override
    public String savePicture(MultipartFile picture) {
        if (picture == null) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        String extension = getExtension(picture);

        UUID uuid = UUID.randomUUID();
        String savePath = SAVE_PATH + "/" + uuid.toString() + "." + extension;


        File file = new File(savePath);

        try {
            picture.transferTo(file);
        } catch (IOException e) {
            //TODO 추가 에러 처리 필요.
            throw new RuntimeException(e);
        }


        return savePath;
    }

    private String getExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int commaIdx = fileName.lastIndexOf(".") + 1;

        if (commaIdx == -1) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        String extension = fileName.substring(commaIdx);

        if (!VALID_EXTENSIONS.contains(extension.toLowerCase())) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        return extension;
    }


}
