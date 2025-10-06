package com.example.community.auth.api.dto;

public record LoginResponse(
        Long memberId,
        String nickname,
        String accessToken,
        String refreshToken
) {
}
