package com.example.community.Post.exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class PostNotFoundException extends GeneralException {
    public PostNotFoundException() {
        super(ErrorStatus.POST_NOT_FOUND);
    }
}
