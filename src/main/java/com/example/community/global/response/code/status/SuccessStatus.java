package com.example.community.global.response.code.status;

import com.example.community.global.response.code.BaseCode;
import com.example.community.global.response.code.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    EMAIL_AVAILABLE(HttpStatus.OK, "200_001", "사용 가능한 이메일입니다."),
    EMAIL_DUPLICATED(HttpStatus.OK, "200_002", "이미 사용 중인 이메일입니다."),
    NICKNAME_AVAILABLE(HttpStatus.OK, "200_002", "사용 가능한 닉네임입니다."),
    NICKNAME_DUPLICATED(HttpStatus.OK, "200_003", "이미 사용 중인 닉네임입니다."),
    SIGNUP_SUCCESS(HttpStatus.OK, "200_004", "회원가입이 완료되었습니다."),

    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
