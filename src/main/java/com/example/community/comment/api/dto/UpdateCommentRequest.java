package com.example.community.comment.api.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCommentRequest(
        @NotBlank(message = "내용은 필수입니다.")
        String content
) {
}
