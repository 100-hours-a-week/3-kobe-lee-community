package com.example.community.comment.api;

import com.example.community.comment.api.dto.CreateCommentRequest;
import com.example.community.comment.api.dto.CreateCommentResponse;
import com.example.community.comment.application.mapper.CreateCommentMapper;
import com.example.community.comment.application.service.CommentService;
import com.example.community.comment.domain.Comment;
import com.example.community.global.response.ApiResponse;
import com.example.community.global.response.code.status.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ApiResponse<CreateCommentResponse> createComment(HttpServletRequest httpServletRequest,
                                                            @RequestBody @Valid CreateCommentRequest createCommentRequest,
                                                            @PathVariable Long postId) {
        Comment comment = commentService.createComment(httpServletRequest, createCommentRequest, postId);
        return ApiResponse.onSuccess(SuccessStatus.CREATE_COMMENT, CreateCommentMapper.toCreateCommentResponse(comment));
    }
}
