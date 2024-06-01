package com.todaycloud.todaycloud.feed.controller;


import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.common.response.ResponseDto;
import com.todaycloud.todaycloud.feed.controller.dto.CreateFeedRequestDto;
import com.todaycloud.todaycloud.feed.service.PictureService;
import com.todaycloud.todaycloud.feed.service.dto.PictureDto;
import com.todaycloud.todaycloud.user.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PictureController {

    private final UserService userService;
    private final PictureService pictureService;
    public static final String IMAGE_URL = "http://34.64.187.145:8080/image";

    @Parameters({
            @Parameter(name="userId", in = ParameterIn.HEADER),
            @Parameter(name="password", in = ParameterIn.HEADER)
    })
    @GetMapping(path = "/image")
    public ResponseEntity<InputStreamResource> createFeed(@RequestHeader(name = "userId") String userId, @RequestHeader(name = "password") String password,
                                                          @RequestParam(name = "id") Long id) {
        userService.login(userId, password);
        PictureDto pictureDto = pictureService.getPicture(id);


        return ResponseEntity.ok().contentType(pictureDto.mediaType()).body(pictureDto.resource());
    }

}
