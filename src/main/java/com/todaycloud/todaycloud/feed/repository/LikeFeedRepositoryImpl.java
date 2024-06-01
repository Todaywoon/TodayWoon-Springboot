package com.todaycloud.todaycloud.feed.repository;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.feed.domain.Feed;
import com.todaycloud.todaycloud.feed.domain.LikeFeed;
import com.todaycloud.todaycloud.feed.domain.LikeFeedRepository;
import com.todaycloud.todaycloud.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LikeFeedRepositoryImpl implements LikeFeedRepository {

    private final JpaLikeFeedRepository jpaLikeFeedRepository;

    @Override
    public void save(LikeFeed likeFeed) {
        jpaLikeFeedRepository.save(likeFeed);
    }

    @Override
    public void delete(LikeFeed likeFeed) {
        jpaLikeFeedRepository.delete(likeFeed);
    }

    @Override
    public LikeFeed findById(Long id) {
        return jpaLikeFeedRepository.findById(id).orElseThrow(() -> new ResponseException(ErrorCode.NOT_EXIST_LIKE));
    }

    @Override
    public Optional<LikeFeed> findByUserAndFeed(User user, Feed feed) {
        return jpaLikeFeedRepository.findByUserAndFeed(user, feed);
    }
}
