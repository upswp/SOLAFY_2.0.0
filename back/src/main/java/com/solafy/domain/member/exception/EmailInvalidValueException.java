package com.solafy.domain.member.exception;

import com.solafy.global.error.exception.ErrorCode;
import com.solafy.global.error.exception.InvalidValueException;

public class EmailInvalidValueException extends InvalidValueException {

    public EmailInvalidValueException(final String email) {
        super(email, ErrorCode.SIGNUP_INPUT_INVALID);
    }
}
