package com.solafy.domain.member.exception;

import com.solafy.global.error.exception.BusinessException;
import com.solafy.global.error.exception.ErrorCode;
public class RoleWrongException extends BusinessException {

    public RoleWrongException() {
        super(ErrorCode.ROLE_WRONG);
    }
}