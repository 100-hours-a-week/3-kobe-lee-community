package com.example.community.member.api.dto;

public record InfoResponse(
        String email,
        String nickname,
        Long profileImageId
) {
}
