package com.example.community.Post.api;

import com.example.community.Post.api.dto.CreatePostRequest;
import com.example.community.Post.api.dto.CreatePostResponse;
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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
