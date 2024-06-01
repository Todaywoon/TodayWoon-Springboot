package com.todaycloud.todaycloud.user.service;

import com.todaycloud.todaycloud.user.domain.User;
import com.todaycloud.todaycloud.user.service.dto.UserDto;

public interface UserService {
    UserDto register(String userId, String password);
    UserDto login(String userId, String password);

    User findUser(UserDto userDto);
}
