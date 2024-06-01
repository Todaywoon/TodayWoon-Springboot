package com.todaycloud.todaycloud.feed.repository;

import com.todaycloud.todaycloud.feed.domain.Feed;
import com.todaycloud.todaycloud.feed.domain.FeedWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FeeWriteRepositoryImpl implements FeedWriteRepository {

    private final JpaFeedRepository jpaFeedRepository;

    @Override
    public Feed save(Feed feed) {
        return jpaFeedRepository.save(feed);
    }
}
