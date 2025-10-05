package com.example.community.member.application.service;

import com.example.community.global.config.AppProperties;
import com.example.community.image.domain.Image;
import com.example.community.image.repository.ImageRepository;
import com.example.community.member.api.dto.SignUpRequest;
import com.example.community.member.application.mapper.SignUpMapper;
import com.example.community.member.domain.Member;
import com.example.community.member.exception.DefaultImageNotFoundException;
import com.example.community.member.exception.DuplicateEmailException;
import com.example.community.member.exception.DuplicateNicknameException;
import com.example.community.member.exception.PasswordMismatchException;
import com.example.community.member.repository.MemberRepository;
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

    @Override
    @Transactional(readOnly = true)
    public boolean emailDuplicateCheck(String email) {
        return !memberRepository.existsByEmail(email);
    }

    @Override
    public boolean nicknameDuplicateCheck(String nickname) {
        return !memberRepository.existsByNickname(nickname);
    }

    @Override
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
}
