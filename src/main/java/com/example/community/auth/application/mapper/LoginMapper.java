package com.example.community.auth.application.mapper;

import com.example.community.auth.api.dto.LoginResponse;
import com.example.community.auth.jwt.JwtToken;
import com.example.community.member.domain.Member;

public class LoginMapper {
    public static LoginResponse toLoginResponse(Member member, JwtToken jwtToken) {
        return new LoginResponse(member.getId(), member.getNickname(), jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }
}
