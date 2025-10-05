package com.example.community.member.application.service;

public interface MemberService {
    boolean emailDuplicateCheck(String email);

    boolean nicknameDuplicateCheck(String nickname);

}
