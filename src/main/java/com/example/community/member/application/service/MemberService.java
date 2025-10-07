package com.example.community.member.application.service;

import com.example.community.member.api.dto.SignUpRequest;
import com.example.community.member.domain.Member;

public interface MemberService {
    boolean emailDuplicateCheck(String email);

    boolean nicknameDuplicateCheck(String nickname);

    Member signUp(SignUpRequest signUpRequest);

    Member getMemberInfo(Long memberId);
}
