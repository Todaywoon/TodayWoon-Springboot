package com.todaycloud.todaycloud.feed.service;

import com.todaycloud.todaycloud.user.service.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface FeedWriteService {
    Long createFeed(String id, String password, LocalDateTime startTime, LocalDateTime finishTime, MultipartFile image);
    void likeFeed(UserDto userDto, Long feedId);
    void deleteLikeFeed(UserDto userDto, Long feedId);
}
