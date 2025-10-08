package com.example.community.Post.repository;

import com.example.community.Post.api.dto.PostPreview;
import com.example.community.Post.domain.QPost;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QPost post = QPost.post;

    @Override
    public List<PostPreview> findPostsWithCursor(String sort, int limit, Object cursor) {
        BooleanExpression cursorCondition = buildCursorCondition(sort, cursor);
        OrderSpecifier<?> orderSpecifier = buildOrderSpecifier(sort);

        return queryFactory
                .select(Projections.constructor(
                        PostPreview.class,
                        post.title,
                        post.likeCount,
                        post.commentCount,
                        post.viewCount,
                        post.writer.id,
                        post.writer.profileImage.id,
                        post.writer.nickname,
                        post.createdAt
                ))
                .from(post)
                .where(cursorCondition)
                .orderBy(orderSpecifier)
                .limit(limit)
                .fetch();
    }

    private BooleanExpression buildCursorCondition(String sort, Object cursor) {
        if (cursor == null) return null;

        switch (sort) {
            case "createdAt":
                return post.createdAt.lt((LocalDateTime) cursor);
            case "likes":
                return post.likeCount.lt((Long) cursor);
            case "comments":
                return post.commentCount.lt((Long) cursor);
            case "views":
                return post.viewCount.lt((Long) cursor);
            default:
                return null;
        }
    }

    private OrderSpecifier<?> buildOrderSpecifier(String sort) {
        switch (sort) {
            case "createdAt":
                return new OrderSpecifier<>(Order.DESC, post.createdAt);
            case "likes":
                return new OrderSpecifier<>(Order.DESC, post.likeCount);
            case "comments":
                return new OrderSpecifier<>(Order.DESC, post.commentCount);
            case "views":
                return new OrderSpecifier<>(Order.DESC, post.viewCount);
            default:
                return new OrderSpecifier<>(Order.DESC, post.createdAt);
        }
    }
}
