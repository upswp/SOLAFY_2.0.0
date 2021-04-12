package com.solafy.domain.member.exception;

import javax.persistence.EntityNotFoundException;

public class EmailNotFoundException extends EntityNotFoundException {

    public EmailNotFoundException(String target) {
        super(target + " is not found");
    }
}
