package com.todaycloud.todaycloud.user.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserId(String userId);
    void save(User user);
}
