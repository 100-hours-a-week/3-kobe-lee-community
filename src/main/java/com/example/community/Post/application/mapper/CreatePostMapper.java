package com.example.community.Post.application.mapper;

import com.example.community.Post.api.dto.CreatePostResponse;
import com.example.community.Post.domain.Post;
import com.example.community.postImage.api.dto.PostImageResponse;
import java.util.List;

public class CreatePostMapper {
    public static CreatePostResponse toCreatePostResponse(Post post) {
        List<PostImageResponse> postImageList = post.getPostImageList().stream()
                .map(postImage -> new PostImageResponse(
                        postImage.getImage().getId(),
                        postImage.getDisplayOrder(),
                        postImage.isThumbnail()
                ))
                .toList();

        return new CreatePostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                postImageList
        );
    }
}
