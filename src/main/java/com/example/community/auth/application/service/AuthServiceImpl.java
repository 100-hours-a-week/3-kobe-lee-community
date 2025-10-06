package com.example.community.auth.application.service;

import com.example.community.auth.Exception.LoginFailedException;
import com.example.community.auth.api.dto.LoginRequest;
import com.example.community.auth.api.dto.LoginResponse;
import com.example.community.auth.application.mapper.LoginMapper;
import com.example.community.auth.jwt.JwtToken;
import com.example.community.auth.jwt.JwtUtils;
import com.example.community.global.redis.RedisDao;
import com.example.community.global.response.code.status.ErrorStatus;
import com.example.community.member.domain.Member;
import com.example.community.member.exception.MemberNotFoundException;
import com.example.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtils jwtUtils;


    @Override
    public LoginResponse login(LoginRequest request) {
        String email = request.email();
        String rawPassword = request.password();
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new LoginFailedException();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, rawPassword);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        JwtToken jwtToken = jwtUtils.generateToken(authentication);

        return LoginMapper.toLoginResponse(member, jwtToken);
    }
}
