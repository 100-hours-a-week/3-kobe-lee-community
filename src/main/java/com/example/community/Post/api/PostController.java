package com.example.community.Post.api;

import com.example.community.Post.api.dto.CreatePostRequest;
import com.example.community.Post.api.dto.CreatePostResponse;
import com.example.community.Post.api.dto.PostPreview;
import com.example.community.Post.api.dto.UpdatePostRequest;
import com.example.community.Post.api.dto.UpdatePostResponse;
import com.example.community.Post.application.mapper.CreatePostMapper;
import com.example.community.Post.application.mapper.UpdatePostMapper;
import com.example.community.Post.application.service.PostService;
import com.example.community.Post.domain.Post;
import com.example.community.global.response.ApiResponse;
import com.example.community.global.response.code.status.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ApiResponse<CreatePostResponse> createPost(HttpServletRequest httpServletRequest,
                                                      @RequestBody @Valid CreatePostRequest createPostRequest) {
        Post post = postService.createPost(httpServletRequest, createPostRequest);
        return ApiResponse.onSuccess(SuccessStatus.CREATE_POST, CreatePostMapper.toCreatePostResponse(post));
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<LocalDateTime> deletePost(HttpServletRequest httpServletRequest,
                                                 @PathVariable Long postId) {
        return ApiResponse.onSuccess(SuccessStatus.DELETE_POST, postService.deletePost(httpServletRequest, postId));
    }

    @PatchMapping("/{postId}")
    public ApiResponse<UpdatePostResponse> updatePost(HttpServletRequest httpServletRequest,
                                                      @RequestBody @Valid UpdatePostRequest updatePostRequest,
                                                      @PathVariable Long postId) {
        Post post = postService.updatePost(httpServletRequest, updatePostRequest, postId);
        return ApiResponse.onSuccess(SuccessStatus.UPDATE_POST, UpdatePostMapper.toUpdatePostResponse(post));
    }

    @GetMapping
    public ApiResponse<List<PostPreview>> getPostList(
            @RequestParam(defaultValue = "createdAt") String sort,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String cursor) {

        Object cursorValue = parseCursor(sort, cursor);

        List<PostPreview> posts = postService.getPostList(sort, limit, cursorValue);
        return ApiResponse.onSuccess(SuccessStatus.GET_POST_LIST, posts);
    }

    private Object parseCursor(String sort, String cursor) {
        if (cursor == null) return null;

        return switch (sort) {
            case "createdAt" -> LocalDateTime.parse(cursor);
            case "likes", "comments", "views" -> Long.parseLong(cursor);
            default -> throw new IllegalArgumentException("지원하지 않는 정렬 기준입니다: " + sort);
        };
    }
}
