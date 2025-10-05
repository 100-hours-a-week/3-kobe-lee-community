package com.example.community.member.exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class DuplicateNicknameException extends GeneralException {
    public DuplicateNicknameException() {
        super(ErrorStatus.DUPLICATE_NICKNAME);
    }
}
