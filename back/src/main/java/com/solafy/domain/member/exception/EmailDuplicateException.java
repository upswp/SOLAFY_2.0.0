package com.solafy.domain.member.exception;

import com.solafy.global.error.exception.ErrorCode;
import com.solafy.global.error.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {

    public EmailDuplicateException(final String email) {
        super(email, ErrorCode.EMAIL_DUPLICATION);
    }
}
