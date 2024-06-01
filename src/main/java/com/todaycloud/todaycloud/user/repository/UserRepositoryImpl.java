package com.todaycloud.todaycloud.user.repository;

import com.todaycloud.todaycloud.user.domain.User;
import com.todaycloud.todaycloud.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findByUserId(String userId) {
        return jpaUserRepository.findByUserId(userId);
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(user);
    }
}
