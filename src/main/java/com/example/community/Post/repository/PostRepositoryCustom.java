package com.example.community.Post.repository;

import com.example.community.Post.api.dto.GetPostResponse;
import com.example.community.Post.api.dto.PostPreview;
import java.util.List;

public interface PostRepositoryCustom {
    List<PostPreview> findPostsWithCursor(String sort, int limit, Object cursor);

    GetPostResponse findPostDetail(Long postId, Long viewerId);
}