package com.todaycloud.todaycloud.feed.controller;


import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.common.response.ResponseDto;
import com.todaycloud.todaycloud.feed.controller.dto.CreateFeedRequestDto;
import com.todaycloud.todaycloud.feed.domain.FeedWriteRepository;
import com.todaycloud.todaycloud.feed.service.FeedCreateService;
import com.todaycloud.todaycloud.feed.service.PictureCreateService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.SimpleFormatter;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@RestController
@RequiredArgsConstructor
public class FeedCreateController {

    private final FeedCreateService feedCreateService;
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

        Long feedId = feedCreateService.createFeed(userId, password, startTime, finishTime, file);

        return ResponseEntity.ok().body(new ResponseDto<>(feedId));
    }



    private void assertValidBody(CreateFeedRequestDto request) {
        if (request.finishTime() == null || request.startTime() == null) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }
    }

}
