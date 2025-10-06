package com.example.community.member.exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class DuplicateEmailException extends GeneralException {
    public DuplicateEmailException() {
        super(ErrorStatus.DUPLICATE_EMAIL);
    }
}
