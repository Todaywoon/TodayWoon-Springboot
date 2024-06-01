package com.todaycloud.todaycloud.feed.service;

import com.todaycloud.todaycloud.feed.domain.Feed;

public interface FeedReadService {

    Feed findByFeedId(Long feedId);
}
