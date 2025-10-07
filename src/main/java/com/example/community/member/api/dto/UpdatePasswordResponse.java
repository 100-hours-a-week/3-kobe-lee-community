package com.example.community.member.api.dto;

import java.time.LocalDateTime;

public record UpdatePasswordResponse(
        LocalDateTime updatedAt
) {
}
