package com.todaycloud.todaycloud.feed.service.impl;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.feed.domain.*;
import com.todaycloud.todaycloud.feed.service.FeedReadService;
import com.todaycloud.todaycloud.feed.service.FeedWriteService;
import com.todaycloud.todaycloud.feed.service.PictureCreateService;
import com.todaycloud.todaycloud.user.domain.User;
import com.todaycloud.todaycloud.user.service.UserService;
import com.todaycloud.todaycloud.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FeedWriteServiceImpl implements FeedWriteService {

    private final FeedWriteRepository feedWriteRepository;
    private final FeedReadRepository feedReadRepository;

    private final LikeFeedRepository likeFeedRepository;

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

    @Override
    //TODO 많은 유저가 한 번에 좋아요 할 경우, 몇 개 안 될 수 있음(row-based lock 등 필요)
    @Transactional
    public void likeFeed(UserDto userDto, Long feedId) {
        User user = userService.findUser(userDto);

        Feed feed = feedReadRepository.fidByFeedId(feedId).orElseThrow(() -> new ResponseException(ErrorCode.NOT_EXIST_FEED));

        likeFeedRepository.findByUserAndFeed(user, feed).ifPresent(likeFeed -> {throw new ResponseException(ErrorCode.NOT_EXIST_LIKE);});

        LikeFeed likeFeed = LikeFeed.builder()
                .feed(feed)
                .user(user)
                .build();

        feed.incrementLikeCount();

        feedWriteRepository.save(feed);
        likeFeedRepository.save(likeFeed);
    }

    @Override
    @Transactional
    public void deleteLikeFeed(UserDto userDto, Long feedId) {
        User user = userService.findUser(userDto);

        Feed feed = feedReadRepository.fidByFeedId(feedId).orElseThrow(() -> new ResponseException(ErrorCode.NOT_EXIST_FEED));
        LikeFeed likeFeed = likeFeedRepository.findByUserAndFeed(user, feed).orElseThrow(() -> new ResponseException(ErrorCode.NOT_EXIST_LIKE));

        feed.decrementLikeCount();

        likeFeedRepository.delete(likeFeed);
        feedWriteRepository.save(feed);
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

        if (Duration.between(startTime, finishTime).toHours() >= 24) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }
    }
}
