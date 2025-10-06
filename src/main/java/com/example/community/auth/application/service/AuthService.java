package com.example.community.auth.application.service;

import com.example.community.auth.api.dto.LoginRequest;
import com.example.community.auth.api.dto.LoginResponse;
import com.example.community.auth.api.dto.LogoutResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    public LoginResponse login(LoginRequest request);

    public LogoutResponse logout(HttpServletRequest request);
}
