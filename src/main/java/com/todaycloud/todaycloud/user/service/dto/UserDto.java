package com.todaycloud.todaycloud.user.service.dto;

import com.todaycloud.todaycloud.user.domain.User;


public record UserDto(String userId) {

    public UserDto(User user) {
        this(user.getUserId());
    }

}
