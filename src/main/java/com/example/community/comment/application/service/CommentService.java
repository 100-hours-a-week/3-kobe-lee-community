package com.example.community.comment.application.service;

import com.example.community.comment.api.dto.CreateCommentRequest;
import com.example.community.comment.domain.Comment;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public interface CommentService {
    Comment createComment(HttpServletRequest httpServletRequest, CreateCommentRequest createCommentRequest,
                          Long postId);

    LocalDateTime deleteComment(HttpServletRequest httpServletRequest, Long postId, Long commentId);
}
