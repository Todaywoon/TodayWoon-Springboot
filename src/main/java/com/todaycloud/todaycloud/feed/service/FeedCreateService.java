package com.todaycloud.todaycloud.feed.service;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface FeedCreateService {
    Long createFeed(String id, String password, LocalDateTime startTime, LocalDateTime finishTime, MultipartFile image);
}
