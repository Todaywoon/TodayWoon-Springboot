package com.todaycloud.todaycloud.feed.service;

import com.todaycloud.todaycloud.feed.domain.Feed;
import com.todaycloud.todaycloud.feed.service.dto.FeedDto;
import com.todaycloud.todaycloud.feed.service.dto.FeedsDto;
import com.todaycloud.todaycloud.feed.service.dto.MyPageInfoDto;
import com.todaycloud.todaycloud.user.service.dto.UserDto;

import java.util.List;

public interface FeedReadService {

    FeedDto findByFeedId(Long feedId);

    List<FeedsDto> findAll();

    List<FeedsDto> findMyFeeds(UserDto userDto);

    MyPageInfoDto getMyPageInfoThisMonth(UserDto userDto);
}
