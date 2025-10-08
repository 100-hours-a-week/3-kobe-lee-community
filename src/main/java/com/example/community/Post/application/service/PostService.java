package com.example.community.Post.application.service;

import com.example.community.Post.api.dto.CreatePostRequest;
import com.example.community.Post.domain.Post;
import jakarta.servlet.http.HttpServletRequest;

public interface PostService {
    Post createPost(HttpServletRequest httpServletRequest, CreatePostRequest createPostRequest);
}
