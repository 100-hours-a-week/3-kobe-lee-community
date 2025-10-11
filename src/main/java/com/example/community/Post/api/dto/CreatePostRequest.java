package com.example.community.Post.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public record CreatePostRequest(
        @Size(max = 26, message = "제목은 최대 26자까지 작성 가능합니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @NotEmpty(message = "최소 한 장 이상의 이미지가 필요합니다.")
        List<Long> imageIds,  // 이미지 업로드 후 DB에 저장된 imageId 리스트

        Long thumbnailImageId
) {
}
