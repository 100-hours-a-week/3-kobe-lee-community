package com.example.community.auth.api;

import com.example.community.auth.api.dto.LoginRequest;
import com.example.community.auth.api.dto.LoginResponse;
import com.example.community.auth.application.service.AuthService;
import com.example.community.global.response.ApiResponse;
import com.example.community.global.response.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);
        return ApiResponse.onSuccess(SuccessStatus.LOGIN_SUCCESS, loginResponse);
    }
}
