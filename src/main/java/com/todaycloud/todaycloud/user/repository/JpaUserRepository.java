package com.todaycloud.todaycloud.user.repository;

import com.todaycloud.todaycloud.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
}
