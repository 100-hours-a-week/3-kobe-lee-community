package com.example.community.member.application.service;

import com.example.community.auth.jwt.JwtUtils;
import com.example.community.global.config.AppProperties;
import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;
import com.example.community.image.domain.Image;
import com.example.community.image.repository.ImageRepository;
import com.example.community.member.api.dto.SignUpRequest;
import com.example.community.member.api.dto.UpdateInfoRequest;
import com.example.community.member.application.mapper.SignUpMapper;
import com.example.community.member.domain.Member;
import com.example.community.member.exception.DefaultImageNotFoundException;
import com.example.community.member.exception.DuplicateEmailException;
import com.example.community.member.exception.DuplicateNicknameException;
import com.example.community.member.exception.MemberNotFoundException;
import com.example.community.member.exception.PasswordMismatchException;
import com.example.community.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppProperties appProperties;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional(readOnly = true)
    public boolean emailDuplicateCheck(String email) {
        return !memberRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean nicknameDuplicateCheck(String nickname) {
        return !memberRepository.existsByNickname(nickname);
    }

    @Override
    @Transactional
    public Member signUp(SignUpRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException();
        }

        if (!request.password().equals(request.confirmPassword())) {
            throw new PasswordMismatchException();
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        if (memberRepository.existsByNickname(request.nickname())) {
            throw new DuplicateNicknameException();
        }

        Long imageId;
        if (request.imageId() != null && imageRepository.existsById(request.imageId())) {
            imageId = request.imageId();
        } else {
            imageId = appProperties.getDefaultProfileImageId();
        }

        Image profileImage = imageRepository.findById(imageId)
                .orElseThrow(DefaultImageNotFoundException::new);
        Member member = SignUpMapper.toMember(request, profileImage, encodedPassword);
        memberRepository.save(member);
        return member;
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMemberInfo(Long memberId) {

        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    @Transactional
    public Member updateInfo(HttpServletRequest httpServletRequest, UpdateInfoRequest updateInfoRequest) {
        String accessToken = jwtUtils.resolveToken(httpServletRequest);
        String memberId = jwtUtils.getUserNameFromToken(accessToken);
        Member member = memberRepository.findById(Long.parseLong(memberId)).orElseThrow(MemberNotFoundException::new);
        if (updateInfoRequest.nickname() != null) {
            member.updateNickname(updateInfoRequest.nickname());
        }

        if (updateInfoRequest.profileImageId() != null) {
            Image profileImage = imageRepository.findById(updateInfoRequest.profileImageId())
                    .orElseThrow(() -> new GeneralException(ErrorStatus.IMAGE_NOT_FOUND));
            member.updateProfileImage(profileImage);
        }

        return member;
    }
}
