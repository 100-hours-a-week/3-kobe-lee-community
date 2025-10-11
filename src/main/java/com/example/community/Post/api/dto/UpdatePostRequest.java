package com.example.community.Post.api.dto;

import jakarta.validation.constraints.Size;

public record UpdatePostRequest(
        @Size(max = 26, message = "제목은 최대 26자까지 작성 가능합니다.")
        String title,

        String content
) {
}
