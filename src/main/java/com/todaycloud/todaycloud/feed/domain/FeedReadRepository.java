package com.todaycloud.todaycloud.feed.domain;

import com.todaycloud.todaycloud.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface FeedReadRepository {
    Optional<Feed> fidByFeedId(Long feedId);
    List<Feed> findAll();
    List<Feed> findMyFeeds(User user);
}
