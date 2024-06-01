package com.todaycloud.todaycloud.feed.repository;

import com.todaycloud.todaycloud.feed.domain.Feed;
import com.todaycloud.todaycloud.user.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaFeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findByUser(User user, Sort sort);
}
