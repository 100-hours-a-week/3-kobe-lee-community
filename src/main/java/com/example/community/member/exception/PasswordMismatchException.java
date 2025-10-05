package com.example.community.member.exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class PasswordMismatchException extends GeneralException {
    public PasswordMismatchException() {
        super(ErrorStatus.PASSWORD_MISMATCH);
    }
}
