package com.example.community.comment.application.service;

import com.example.community.comment.api.dto.CommentResponse;
import com.example.community.comment.api.dto.CreateCommentRequest;
import com.example.community.comment.api.dto.UpdateCommentRequest;
import com.example.community.comment.domain.Comment;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface CommentService {
    Comment createComment(HttpServletRequest httpServletRequest, CreateCommentRequest createCommentRequest,
                          Long postId);

    LocalDateTime deleteComment(HttpServletRequest httpServletRequest, Long postId, Long commentId);

    Comment updateComment(HttpServletRequest httpServletRequest, UpdateCommentRequest updateCommentRequest,
                          Long commentId);

    List<CommentResponse> getCommentList(HttpServletRequest httpServletRequest, Long postId, String sort,
                                         int limit, LocalDateTime cursorCreatedAt,
                                         Long cursorId);
}
