package com.todaycloud.todaycloud.feed.repository;

import com.todaycloud.todaycloud.feed.domain.Feed;
import com.todaycloud.todaycloud.feed.domain.LikeFeed;
import com.todaycloud.todaycloud.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaLikeFeedRepository extends JpaRepository<LikeFeed, Long> {
    Optional<LikeFeed> findByUserAndFeed(User user, Feed feed);
}
