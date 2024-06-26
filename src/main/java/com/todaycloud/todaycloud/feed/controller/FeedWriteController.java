package com.todaycloud.todaycloud.feed.controller;


import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.common.response.ResponseDto;
import com.todaycloud.todaycloud.feed.controller.dto.CreateFeedRequestDto;
import com.todaycloud.todaycloud.feed.service.FeedWriteService;
import com.todaycloud.todaycloud.user.service.UserService;
import com.todaycloud.todaycloud.user.service.dto.UserDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedWriteController {

    private final FeedWriteService feedWriteService;
    private final UserService userService;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Parameters({
            @Parameter(name="userId", in = ParameterIn.HEADER),
            @Parameter(name="password", in = ParameterIn.HEADER)
    })
    @PostMapping(path = "/feed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto<Long>> createFeed(@RequestHeader(name = "userId") String userId, @RequestHeader(name = "password") String password,
                                                        @RequestPart(name = "image") MultipartFile file,
                                                        @RequestPart(name = "body") CreateFeedRequestDto requestBody) {
        LocalDateTime startTime;
        LocalDateTime finishTime;

        try {
            startTime = LocalDateTime.parse(requestBody.startTime(), timeFormatter);
            finishTime = LocalDateTime.parse(requestBody.finishTime(), timeFormatter);
        } catch (Exception ex) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        Long feedId = feedWriteService.createFeed(userId, password, startTime, finishTime, file);

        return ResponseEntity.ok().body(new ResponseDto<>(feedId));
    }



    @Parameters({
            @Parameter(name="userId", in = ParameterIn.HEADER),
            @Parameter(name="password", in = ParameterIn.HEADER)
    })
    @GetMapping(path = "/like")
    public ResponseEntity<ResponseDto<Boolean>> likeFeed(@RequestHeader(name = "userId") String userId, @RequestHeader(name = "password") String password,
                                                        @RequestParam(name="feed_id") Long feed_id) {

        UserDto userDto = userService.login(userId, password);


        feedWriteService.likeFeed(userDto, feed_id);

        return ResponseEntity.ok().body(new ResponseDto<>(true));
    }



    @Parameters({
            @Parameter(name="userId", in = ParameterIn.HEADER),
            @Parameter(name="password", in = ParameterIn.HEADER)
    })
    @DeleteMapping(path = "/like")
    public ResponseEntity<ResponseDto<Boolean>> deleteLikeFeed(@RequestHeader(name = "userId") String userId, @RequestHeader(name = "password") String password,
                                                         @RequestParam(name="feed_id") Long feed_id) {

        UserDto userDto = userService.login(userId, password);


        feedWriteService.deleteLikeFeed(userDto, feed_id);

        return ResponseEntity.ok().body(new ResponseDto<>(true));
    }

}
