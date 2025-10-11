package com.example.community.comment.application.service;

import com.example.community.comment.api.dto.CreateCommentRequest;
import com.example.community.comment.domain.Comment;
import jakarta.servlet.http.HttpServletRequest;

public interface CommentService {
    Comment createComment(HttpServletRequest httpServletRequest, CreateCommentRequest createCommentRequest, Long postId);
}
