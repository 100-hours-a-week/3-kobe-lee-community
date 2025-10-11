package com.example.community.comment.application.mapper;

import com.example.community.Post.domain.Post;
import com.example.community.comment.api.dto.CreateCommentRequest;
import com.example.community.comment.api.dto.CreateCommentResponse;
import com.example.community.comment.domain.Comment;
import com.example.community.member.domain.Member;

public class CreateCommentMapper {
    public static Comment toComment(CreateCommentRequest createCommentRequest, Member member, Post post) {
        return Comment.builder()
                .content(createCommentRequest.content())
                .writer(member)
                .post(post)
                .build();
    }

    public static CreateCommentResponse toCreateCommentResponse(Comment comment) {
        return new CreateCommentResponse(comment.getId(), comment.getCreatedAt());
    }
}
