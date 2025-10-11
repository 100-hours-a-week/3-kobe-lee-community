package com.example.community.Post.api.dto;

import com.example.community.postImage.api.dto.PostImageResponse;
import java.util.List;

public record CreatePostResponse(
        Long postId,
        String title,
        String content,
        List<PostImageResponse> PostImageResponseList
) {
}
