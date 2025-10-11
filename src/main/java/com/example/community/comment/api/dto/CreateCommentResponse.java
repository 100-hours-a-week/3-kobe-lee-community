package com.example.community.comment.api.dto;

import java.time.LocalDateTime;

public record CreateCommentResponse(
        Long commentId,
        LocalDateTime createdAt
) {
}
