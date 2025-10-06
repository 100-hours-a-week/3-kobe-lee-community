package com.example.community.auth.application.service;

import com.example.community.auth.api.dto.LoginRequest;
import com.example.community.auth.api.dto.LoginResponse;

public interface AuthService {
    public LoginResponse login(LoginRequest request);
}
