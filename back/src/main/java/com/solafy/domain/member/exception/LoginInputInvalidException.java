package com.solafy.domain.member.exception;

import com.solafy.global.error.exception.BusinessException;
import com.solafy.global.error.exception.ErrorCode;

public class LoginInputInvalidException extends BusinessException {

    public LoginInputInvalidException() {
        super(ErrorCode.LOGIN_INPUT_INVALID);
    }
}
