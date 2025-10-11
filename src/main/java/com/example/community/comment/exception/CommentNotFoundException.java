package com.example.community.comment.exception;

import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;

public class CommentNotFoundException extends GeneralException {
    public CommentNotFoundException() {
        super(ErrorStatus.COMMENT_NOT_FOUND);
    }
}
