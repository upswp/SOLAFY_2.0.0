package com.solafy.domain.member.exception;

import com.solafy.global.error.exception.BusinessException;
import com.solafy.global.error.exception.ErrorCode;

import javax.persistence.EntityNotFoundException;

//public class MemberNotFoundException extends EntityNotFoundException {
//
//    public MemberNotFoundException(Long target) {
//        super(target + " is not found");
//    }
//}

public class MemberNotFoundException extends BusinessException{

    public MemberNotFoundException(final Long target) {
        super(target.toString(), ErrorCode.ENTITY_NOT_FOUND);
    }
}