package com.example.community.comment.application.mapper;

import com.example.community.comment.api.dto.DeleteCommentResponse;
import java.time.LocalDateTime;

public class DeleteCommentMapper {
    public static DeleteCommentResponse toDeleteCommentResponse(LocalDateTime deletedAt) {
        return new DeleteCommentResponse(deletedAt);
    }
}
