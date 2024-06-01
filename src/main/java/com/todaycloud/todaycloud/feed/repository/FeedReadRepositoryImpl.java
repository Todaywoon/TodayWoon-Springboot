package com.todaycloud.todaycloud.feed.repository;

import com.todaycloud.todaycloud.feed.domain.Feed;
import com.todaycloud.todaycloud.feed.domain.FeedReadRepository;
import com.todaycloud.todaycloud.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FeedReadRepositoryImpl implements FeedReadRepository {

    private final JpaFeedRepository jpaFeedRepository;

    @Override
    public Optional<Feed> fidByFeedId(Long feedId) {
        return jpaFeedRepository.findById(feedId);
    }

    @Override
    public List<Feed> findAll() {
        return jpaFeedRepository.findAll(Sort.by(Sort.Direction.DESC, "finishTime"));
    }

    @Override
    public List<Feed> findMyFeeds(User user) {
        return jpaFeedRepository.findByUser(user, Sort.by(Sort.Direction.DESC, "finishTime"));
    }

    @Override
    public List<Feed> findByUserAndFinishTimeBetween(User user, LocalDateTime minTime, LocalDateTime maxTime) {
        return jpaFeedRepository.findByUserAndFinishTimeBetween(user, minTime, maxTime);
    }
}
