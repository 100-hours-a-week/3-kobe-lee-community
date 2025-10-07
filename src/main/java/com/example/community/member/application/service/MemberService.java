package com.example.community.member.application.service;

import com.example.community.member.api.dto.SignUpRequest;
import com.example.community.member.api.dto.UpdateInfoRequest;
import com.example.community.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {
    boolean emailDuplicateCheck(String email);

    boolean nicknameDuplicateCheck(String nickname);

    Member signUp(SignUpRequest signUpRequest);

    Member getMemberInfo(Long memberId);

    Member updateInfo(HttpServletRequest httpServletRequest, UpdateInfoRequest updateInfoRequest);
}
