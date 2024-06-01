package com.todaycloud.todaycloud.feed.service.impl;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.feed.domain.Feed;
import com.todaycloud.todaycloud.feed.domain.FeedWriteRepository;
import com.todaycloud.todaycloud.feed.domain.Picture;
import com.todaycloud.todaycloud.feed.service.FeedCreateService;
import com.todaycloud.todaycloud.feed.service.PictureCreateService;
import com.todaycloud.todaycloud.user.domain.User;
import com.todaycloud.todaycloud.user.service.UserService;
import com.todaycloud.todaycloud.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FeedCreateServiceImpl implements FeedCreateService {

    private final FeedWriteRepository feedWriteRepository;
    private final PictureCreateService pictureCreateService;
    private final UserService userService;

    @Override
    public Long createFeed(String id, String password, LocalDateTime startTime, LocalDateTime finishTime, MultipartFile file) {
        assertValidTime(startTime, finishTime);

        UserDto userDto = userService.login(id, password);
        User user = userService.findUser(userDto);


        Long duration = calculateWalkDuration(startTime, finishTime);
        if (duration  < 1) {
            throw new ResponseException(ErrorCode.INVALID_TIME);
        }

        String savePath = pictureCreateService.savePicture(file);

        LocalDateTime nowTime = LocalDateTime.now();

        Picture picture = Picture.builder()
                .url(savePath)
                .created_at(nowTime)
                .build();

        Feed feed = Feed.builder()
                .duration(duration)
                .startTime(startTime)
                .finishTime(finishTime)
                .user(user)
                .picture(picture)
                .likeCount(0L)
                .build();


        return feedWriteRepository.save(feed).getId();
    }

    //산책 시간 (분) 계산
    private Long calculateWalkDuration(LocalDateTime startTime, LocalDateTime finishTime) {
        Duration walkDuration = Duration.between(startTime, finishTime);

        return walkDuration.toMinutes();
    }

    private void assertValidTime(LocalDateTime startTime, LocalDateTime finishTime) {
        if (startTime == null || finishTime == null) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        if (startTime.isAfter(finishTime)) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }
    }
}
