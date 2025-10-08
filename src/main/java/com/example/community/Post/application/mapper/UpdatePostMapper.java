package com.example.community.Post.application.mapper;

import com.example.community.Post.api.dto.UpdatePostResponse;
import com.example.community.Post.domain.Post;

public class UpdatePostMapper {
    public static UpdatePostResponse toUpdatePostResponse(Post post) {
        return new UpdatePostResponse(post.getTitle(), post.getContent());
    }
}
