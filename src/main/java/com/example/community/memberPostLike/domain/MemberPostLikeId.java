package com.example.community.memberPostLike.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberPostLikeId implements Serializable {

    @EqualsAndHashCode.Include
    private Long memberId;

    @EqualsAndHashCode.Include
    private Long postId;
}
