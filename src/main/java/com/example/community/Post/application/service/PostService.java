package com.example.community.Post.application.service;

import com.example.community.Post.api.dto.CreatePostRequest;
import com.example.community.Post.domain.Post;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public interface PostService {
    Post createPost(HttpServletRequest httpServletRequest, CreatePostRequest createPostRequest);

    LocalDateTime deletePost(HttpServletRequest httpServletRequest, Long postId);
}
