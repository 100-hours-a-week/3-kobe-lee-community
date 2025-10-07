package com.example.community.member.application.mapper;

import com.example.community.member.api.dto.InfoResponse;
import com.example.community.member.api.dto.UpdateInfoResponse;
import com.example.community.member.domain.Member;

public class InfoMapper {
    public static InfoResponse toInfoResponse(Member member) {
        return new InfoResponse(member.getEmail(), member.getNickname(), member.getProfileImage().getId());
    }

    public static UpdateInfoResponse toUpdateInfoResponse(Member member) {
        return new UpdateInfoResponse(member.getNickname(), member.getProfileImage().getId());
    }
}
