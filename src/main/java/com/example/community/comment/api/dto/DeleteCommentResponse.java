package com.example.community.comment.api.dto;

import java.time.LocalDateTime;

public record DeleteCommentResponse(
        LocalDateTime deletedAt
){
}
