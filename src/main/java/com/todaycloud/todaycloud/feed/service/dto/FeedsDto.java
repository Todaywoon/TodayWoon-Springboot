package com.todaycloud.todaycloud.feed.service.dto;

import com.todaycloud.todaycloud.feed.controller.PictureController;
import com.todaycloud.todaycloud.feed.domain.Feed;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record FeedsDto(Long feed_id, String user_id, String start_time, String finish_time, Long duration, String picture_url, Long like_count) {
    public FeedsDto(Feed feed) {
        this(feed.getId(), feed.getUser().getUserId() ,parsingTime(feed.getStartTime()), parsingTime(feed.getFinishTime()), feed.getDuration(), createImageUrl(feed.getPicture().getId()), feed.getLikeCount());
    }

    private static String parsingTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  time.format(formatter);
    }

    private static String createImageUrl(Long imageId) {
        return PictureController.IMAGE_URL + "?id=" + imageId;
    }
}
