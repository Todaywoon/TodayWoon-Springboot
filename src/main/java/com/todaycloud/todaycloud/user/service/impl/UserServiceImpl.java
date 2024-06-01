package com.todaycloud.todaycloud.user.service.impl;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.user.domain.User;
import com.todaycloud.todaycloud.user.domain.UserRepository;
import com.todaycloud.todaycloud.user.service.UserService;
import com.todaycloud.todaycloud.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDto register(String userId, String password) {
        assertValidUserIdAndPassword(userId, password);

        userRepository.findByUserId(userId)
                .ifPresent(user -> {throw new ResponseException(ErrorCode.DUPLICATE_USER);});

        User user =  new User(userId, password);

        userRepository.save(user);

        return new UserDto(user);
    }


    //TODO: 토큰 대신, header에 아이디, 비밀번호가 있어서 모든 서비스에서 이용함. aop로 뺄 수 있을 지?
    //다른 서비스에서 이를 이용해서 유저 로그인 성공 여부 가져올 수 있음
    @Override
    public UserDto login(String userId, String password) {
        assertValidUserIdAndPassword(userId, password);

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseException(ErrorCode.MISMATCH_USER_INFO));

        if (!user.getPassword().equals(password)) {
            throw new ResponseException(ErrorCode.MISMATCH_USER_INFO);
        }

        return new UserDto(user);
    }

    //다른 서비스에서 호출 용도....
    @Override
    public User findUser(UserDto userDto) {
        return userRepository.findByUserId(userDto.userId())
                .orElseThrow(() -> new ResponseException(ErrorCode.MISMATCH_USER_INFO));
    }

    private void assertValidUserIdAndPassword(String userId, String password) {
        if (userId == null || password == null) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }
    }
}
