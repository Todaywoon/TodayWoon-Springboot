package com.todaycloud.todaycloud.feed.service.impl;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.feed.domain.Feed;
import com.todaycloud.todaycloud.feed.domain.FeedReadRepository;
import com.todaycloud.todaycloud.feed.service.FeedReadService;
import com.todaycloud.todaycloud.feed.service.dto.FeedDto;
import com.todaycloud.todaycloud.feed.service.dto.FeedsDto;
import com.todaycloud.todaycloud.user.domain.User;
import com.todaycloud.todaycloud.user.service.UserService;
import com.todaycloud.todaycloud.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedReadServiceImpl implements FeedReadService {

    private final FeedReadRepository feedReadRepository;
    private final UserService userService;

    @Override
    public FeedDto findByFeedId(Long feedId) {
        assertValidFeedId(feedId);
        Feed feed = feedReadRepository.fidByFeedId(feedId)
                .orElseThrow(() -> new ResponseException(ErrorCode.NOT_EXIST_FEED));


        return new FeedDto(feed);
    }

    @Override
    public List<FeedsDto> findAll() {
        return feedReadRepository.findAll().stream()
                .map(FeedsDto::new).toList();
    }

    @Override
    public List<FeedsDto> findMyFeeds(UserDto userDto) {
        User user = userService.findUser(userDto);


        return feedReadRepository.findMyFeeds(user).stream()
                .map(FeedsDto::new).toList();
    }


    private void assertValidFeedId(Long feedId) {
        if (feedId == null || feedId < 0) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }
    }
}
