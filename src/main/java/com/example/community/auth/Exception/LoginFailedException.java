package com.example.community.auth.Exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class LoginFailedException extends GeneralException {
    public LoginFailedException() {
        super(ErrorStatus.LOGIN_FAILED);
    }
}
