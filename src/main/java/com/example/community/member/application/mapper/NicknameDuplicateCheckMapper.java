package com.example.community.member.application.mapper;

import com.example.community.member.api.dto.NicknameDuplicateCheckResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NicknameDuplicateCheckMapper {
    public static NicknameDuplicateCheckResponse toResponse(boolean available) {
        return new NicknameDuplicateCheckResponse(available);
    }
}
