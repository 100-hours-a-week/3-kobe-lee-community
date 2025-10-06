package com.example.community.member.exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class MemberNotFoundException extends GeneralException {
    public MemberNotFoundException() {
        super(ErrorStatus.MEMBER_NOT_FOUND);
    }
}
