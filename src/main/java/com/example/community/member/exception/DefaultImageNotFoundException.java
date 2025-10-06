package com.example.community.member.exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class DefaultImageNotFoundException extends GeneralException {
    public DefaultImageNotFoundException() {
        super(ErrorStatus.DEFAULT_IMAGE_NOT_FOUND);
    }
}
