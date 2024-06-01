package com.todaycloud.todaycloud.feed.controller;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.common.response.ResponseDto;
import com.todaycloud.todaycloud.feed.service.FeedReadService;
import com.todaycloud.todaycloud.feed.service.dto.FeedDto;
import com.todaycloud.todaycloud.feed.service.dto.FeedsDto;
import com.todaycloud.todaycloud.user.service.UserService;
import com.todaycloud.todaycloud.user.service.dto.UserDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FeedReadController {

    private final FeedReadService feedReadService;
    private final UserService userService;


    @Parameters({
            @Parameter(name="userId", in = ParameterIn.HEADER),
            @Parameter(name="password", in = ParameterIn.HEADER)
    })
    @GetMapping("/feed")
    public ResponseEntity<ResponseDto<FeedDto>> getFeedDetailInfo(@RequestHeader(name = "userId") String userId, @RequestHeader(name = "password") String password,
                                                                  @RequestParam("field_id") Long fieldId) {
        if (fieldId == null) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        userService.login(userId, password);


        FeedDto feedDto = feedReadService.findByFeedId(fieldId);

        return ResponseEntity.ok().body(new ResponseDto<>(feedDto));
    }

    @Parameters({
            @Parameter(name="userId", in = ParameterIn.HEADER),
            @Parameter(name="password", in = ParameterIn.HEADER)
    })
    @GetMapping("feeds")
    public ResponseEntity<ResponseDto<List<FeedsDto>>> getFeeds(@RequestHeader(name = "userId") String userId, @RequestHeader(name = "password") String password) {
        userService.login(userId, password);

        return ResponseEntity.ok().body(new ResponseDto<>(feedReadService.findAll()));
    }


    @Parameters({
            @Parameter(name="userId", in = ParameterIn.HEADER),
            @Parameter(name="password", in = ParameterIn.HEADER)
    })
    @GetMapping("myfeeds")
    public ResponseEntity<ResponseDto<List<FeedsDto>>> myFeeds(@RequestHeader(name = "userId") String userId, @RequestHeader(name = "password") String password) {
        UserDto userdto = userService.login(userId, password);

        return ResponseEntity.ok().body(new ResponseDto<>(feedReadService.findMyFeeds(userdto)));
    }


}
