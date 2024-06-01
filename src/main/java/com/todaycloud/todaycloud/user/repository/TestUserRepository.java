//package com.todaycloud.todaycloud.user.repository;
//
//import com.todaycloud.todaycloud.user.domain.User;
//import com.todaycloud.todaycloud.user.domain.UserRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class TestUserRepository implements UserRepository {
//
//    private User user = new User("hi", "hi");
//
//    @Override
//    public Optional<User> findByUserId(String userId) {
//
//        if (userId.equals("hi")) {
//            return Optional.of(user);
//        }
//
//        return Optional.empty();
//    }
//
//    @Override
//    public void save(User user) {
//
//    }
//}
