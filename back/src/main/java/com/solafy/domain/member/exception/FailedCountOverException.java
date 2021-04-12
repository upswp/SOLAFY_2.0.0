package com.solafy.domain.member.exception;

import com.solafy.global.error.exception.BusinessException;
import com.solafy.global.error.exception.ErrorCode;

public class FailedCountOverException extends BusinessException {

    public FailedCountOverException() {
        super(ErrorCode.FAILED_COUNT_OVER);
    }
}