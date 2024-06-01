package com.todaycloud.todaycloud.user.controller;


import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.common.response.ResponseDto;
import com.todaycloud.todaycloud.user.controller.dto.LoginDto;
import com.todaycloud.todaycloud.user.controller.dto.RegisterDto;
import com.todaycloud.todaycloud.user.domain.UserRepository;
import com.todaycloud.todaycloud.user.service.UserService;
import com.todaycloud.todaycloud.user.service.dto.UserDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @Parameters({
            @Parameter(name="userId", in = ParameterIn.HEADER),
            @Parameter(name="password", in = ParameterIn.HEADER)
    })
    @GetMapping("/login")
    public ResponseEntity<ResponseDto<UserDto>> login(@RequestHeader(name = "userId") String userId, @RequestHeader(name = "password") String password) {
        LoginDto request = new LoginDto(userId, password);
        assertValidLoginRequest(request);

        UserDto userDto = userService.login(request.userId(), request.password());

        return ResponseEntity.ok(new ResponseDto<>(userDto));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDto<UserDto>> register(@RequestBody RegisterDto request) {
        assertValidRegisterRequest(request);

        UserDto userDto = userService.register(request.userId(), request.password());

        return ResponseEntity.ok(new ResponseDto<>(userDto));

    }



    //TODO dto 내부로 validation 체크 옮길 수 있으면 이동.
    private void assertValidLoginRequest(LoginDto loginDto) {
        if (loginDto.userId() == null || loginDto.password() == null) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }
    }

    private void assertValidRegisterRequest(RegisterDto registerDto) {
        if (registerDto.userId() == null || registerDto.password() == null) {
            throw new ResponseException(ErrorCode.INVALID_FORMAT);
        }
    }
}
