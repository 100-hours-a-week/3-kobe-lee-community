package com.example.community.auth.application.mapper;

import com.example.community.auth.api.dto.LogoutResponse;
import java.time.LocalDateTime;

public class LogoutMapper {
    public static LogoutResponse toLogoutResponse() {
        return new LogoutResponse(LocalDateTime.now());
    }
}
