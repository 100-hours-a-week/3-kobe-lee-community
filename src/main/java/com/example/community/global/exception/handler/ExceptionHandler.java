package com.example.community.global.exception.handler;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.BaseErrorCode;

public class ExceptionHandler extends GeneralException {
    public ExceptionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}