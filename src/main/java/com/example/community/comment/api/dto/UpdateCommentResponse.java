package com.example.community.comment.api.dto;

import java.time.LocalDateTime;

public record UpdateCommentResponse(
        Long commentId,
        LocalDateTime updatedAt
) {
}
