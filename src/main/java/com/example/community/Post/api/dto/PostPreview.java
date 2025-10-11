package com.example.community.Post.api.dto;

import java.time.LocalDateTime;

public record PostPreview(
        String title,
        Long likeCount,
        Long commentCount,
        Long viewCount,
        Long memberId,
        String profileImageId,
        String nickname,
        LocalDateTime createAt
) {
}
