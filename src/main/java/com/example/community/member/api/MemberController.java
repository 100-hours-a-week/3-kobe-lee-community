package com.example.community.member.api;

import com.example.community.global.response.ApiResponse;
import com.example.community.global.response.code.status.SuccessStatus;
import com.example.community.member.api.dto.EmailDuplicateCheckRequest;
import com.example.community.member.api.dto.EmailDuplicateCheckResponse;
import com.example.community.member.api.dto.InfoResponse;
import com.example.community.member.api.dto.NicknameDuplicateCheckRequest;
import com.example.community.member.api.dto.NicknameDuplicateCheckResponse;
import com.example.community.member.api.dto.SignUpRequest;
import com.example.community.member.api.dto.SignUpResponse;
import com.example.community.member.api.dto.UpdateInfoRequest;
import com.example.community.member.api.dto.UpdateInfoResponse;
import com.example.community.member.api.dto.UpdatePasswordRequest;
import com.example.community.member.api.dto.UpdatePasswordResponse;
import com.example.community.member.application.mapper.EmailDuplicateCheckMapper;
import com.example.community.member.application.mapper.InfoMapper;
import com.example.community.member.application.mapper.NicknameDuplicateCheckMapper;
import com.example.community.member.application.mapper.SignUpMapper;
import com.example.community.member.application.service.MemberService;
import com.example.community.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            return ApiResponse.onSuccess(SuccessStatus.EMAIL_AVAILABLE,
                    EmailDuplicateCheckMapper.toResponse(available));
        } else {
            return ApiResponse.onSuccess(SuccessStatus.EMAIL_DUPLICATED,
                    EmailDuplicateCheckMapper.toResponse(available));
        }
    }

    @GetMapping("/nickname")
    public ApiResponse<NicknameDuplicateCheckResponse> nicknameDuplicateCheck(@RequestBody @Valid
                                                                              NicknameDuplicateCheckRequest request) {
        boolean available = memberService.nicknameDuplicateCheck(request.nickname());
        if (available) {
            return ApiResponse.onSuccess(SuccessStatus.NICKNAME_AVAILABLE,
                    NicknameDuplicateCheckMapper.toResponse(available));
        } else {
            return ApiResponse.onSuccess(SuccessStatus.NICKNAME_DUPLICATED,
                    NicknameDuplicateCheckMapper.toResponse(available));
        }
    }

    @PostMapping
    public ApiResponse<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        Member member = memberService.signUp(request);
        return ApiResponse.onSuccess(SuccessStatus.SIGNUP_SUCCESS, SignUpMapper.toSignUpResponse(member));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<InfoResponse> getMemberInfo(@PathVariable Long memberId) {
        Member member = memberService.getMemberInfo(memberId);
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_INFO_FOUND, InfoMapper.toInfoResponse(member));
    }

    @PatchMapping
    public ApiResponse<UpdateInfoResponse> updateInfo(HttpServletRequest httpServletRequest,
                                                      @RequestBody @Valid UpdateInfoRequest infoRequest) {
        Member member = memberService.updateInfo(httpServletRequest, infoRequest);
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_INFO_UPDATE, InfoMapper.toUpdateInfoResponse(member));
    }

    @PutMapping("/password")
    public ApiResponse<UpdatePasswordResponse> updatePassword(HttpServletRequest httpServletRequest,
                                                              @RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        LocalDateTime updatedAt = memberService.updatePassword(httpServletRequest, updatePasswordRequest);
        return ApiResponse.onSuccess(SuccessStatus.PASSWORD_UPDATE, InfoMapper.toUpdatePasswordResponse(updatedAt));
    }

    @DeleteMapping
    public ApiResponse<LocalDateTime> deleteMember(HttpServletRequest httpServletRequest) {
        LocalDateTime deletedAt = memberService.deleteMember(httpServletRequest);
        return ApiResponse.onSuccess(SuccessStatus.DELETE_MEMBER, deletedAt);
    }
}
