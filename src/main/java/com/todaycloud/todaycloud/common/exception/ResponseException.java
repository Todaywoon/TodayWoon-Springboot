package com.todaycloud.todaycloud.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//TODO 추후 널 체크.
@Getter
@RequiredArgsConstructor
public class ResponseException extends RuntimeException {


    private final ErrorCode errorCode;


}
