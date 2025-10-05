package com.example.community.member.application.mapper;

import com.example.community.member.api.dto.EmailDuplicateCheckResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailDuplicateCheckMapper {

    public static EmailDuplicateCheckResponse toResponse(boolean available) {
        return new EmailDuplicateCheckResponse(available);
    }
}