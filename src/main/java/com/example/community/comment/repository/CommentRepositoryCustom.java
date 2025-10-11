package com.example.community.comment.repository;

import com.example.community.comment.api.dto.CommentResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentResponse> getCommentListWithCursor(Long viewerId, Long postId, String sort, int limit, LocalDateTime cursorCreatedAt,
                                                   Long cursorId);
}
