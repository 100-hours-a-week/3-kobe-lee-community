package com.example.community.comment.application.mapper;

import com.example.community.comment.api.dto.UpdateCommentResponse;
import com.example.community.comment.domain.Comment;

public class UpdateCommentMapper {
    public static UpdateCommentResponse toUpdateCommentResponse(Comment comment) {
        return new UpdateCommentResponse(comment.getId(), comment.getUpdatedAt());
    }
}
