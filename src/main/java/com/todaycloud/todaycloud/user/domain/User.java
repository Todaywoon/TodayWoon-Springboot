package com.todaycloud.todaycloud.user.domain;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="MyUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String password;

    public User(String userId, String password) {
        if (userId == null || password == null) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        if (userId.isBlank() || password.isBlank()) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }

        this.userId = userId;
        this.password = password;

    }




}
