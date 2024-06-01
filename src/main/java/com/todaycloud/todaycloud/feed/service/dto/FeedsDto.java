package com.todaycloud.todaycloud.feed.service.dto;

import com.todaycloud.todaycloud.feed.domain.Feed;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record FeedsDto(Long feed_id, String user_id, String start_time, String finish_time, Long duration, String picture_url, Long like_count) {
    public FeedsDto(Feed feed) {
        this(feed.getId(), feed.getUser().getUserId() ,parsingTime(feed.getStartTime()), parsingTime(feed.getFinishTime()), feed.getDuration(), feed.getPicture().getUrl(), feed.getLikeCount());
    }

    private static String parsingTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  time.format(formatter);
    }
}
