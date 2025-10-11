package com.example.community.postImage.api.dto;

public record PostImageResponse(
        Long imageId,
        int displayOrder,
        boolean isThumbnail
) {
}
