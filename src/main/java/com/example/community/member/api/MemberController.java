package com.example.community.member.api;

import com.example.community.global.response.ApiResponse;
import com.example.community.global.response.code.status.SuccessStatus;
import com.example.community.member.api.dto.EmailDuplicateCheckRequest;
import com.example.community.member.api.dto.EmailDuplicateCheckResponse;
import com.example.community.member.api.dto.NicknameDuplicateCheckRequest;
import com.example.community.member.api.dto.NicknameDuplicateCheckResponse;
import com.example.community.member.application.mapper.EmailDuplicateCheckMapper;
import com.example.community.member.application.mapper.NicknameDuplicateCheckMapper;
import com.example.community.member.application.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/email")
    public ApiResponse<EmailDuplicateCheckResponse> emailDuplicateCheck(@RequestBody @Valid
                                                                        EmailDuplicateCheckRequest request) {

        boolean available = memberService.emailDuplicateCheck(request.email());
        if (available) {
            return ApiResponse.onSuccess(SuccessStatus.EMAIL_AVAILABLE, EmailDuplicateCheckMapper.toResponse(available));
        } else {
            return ApiResponse.onSuccess(SuccessStatus.EMAIL_DUPLICATED, EmailDuplicateCheckMapper.toResponse(available));
        }
    }

    @GetMapping("/nickname")
    public ApiResponse<NicknameDuplicateCheckResponse> nicknameDuplicateCheck(@RequestBody @Valid
                                                                              NicknameDuplicateCheckRequest request) {
        boolean available = memberService.nicknameDuplicateCheck(request.nickname());
        if (available) {
            return ApiResponse.onSuccess(SuccessStatus.NICKNAME_AVAILABLE, NicknameDuplicateCheckMapper.toResponse(available));
        } else {
            return ApiResponse.onSuccess(SuccessStatus.NICKNAME_DUPLICATED, NicknameDuplicateCheckMapper.toResponse(available));
        }
    }
}
