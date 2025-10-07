package com.example.community.auth.jwt.exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class InvalidTokenException extends GeneralException {
    public InvalidTokenException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}