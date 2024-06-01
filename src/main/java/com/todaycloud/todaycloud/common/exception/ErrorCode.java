package com.todaycloud.todaycloud.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    //TODO errorCode 분리 필요
    INVALID_FORMAT(1, "포맷이 올바르지 않습니다."),
    DUPLICATE_USER(2, "중복된 유저가 있습니다."),
    MISMATCH_USER_INFO(3, "아이디 또는 패스워드가 다릅니다."),
    NON_LOGIN(4, "유저 아이디, 비밀번호가 올바르지 않습니다."),
    INVALID_TIME(5, "시작 시간, 종료 시간이 올바르지 않습니다."),
    OVER_FILE_SIZE(6, "파일 크기가 너무 큽니다!"),
    NOT_EXIST_FEED(7,"존재하지 않는 피드 입니다.");


    private final int errorCode;
    private final String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
