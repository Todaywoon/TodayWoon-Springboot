package com.todaycloud.todaycloud.feed.domain;

import com.todaycloud.todaycloud.user.domain.User;

import java.util.Optional;

public interface LikeFeedRepository {
    void save(LikeFeed likeFeed);
    void delete(LikeFeed likeFeed);

    LikeFeed findById(Long id);

    Optional<LikeFeed> findByUserAndFeed(User user, Feed feed);
}
