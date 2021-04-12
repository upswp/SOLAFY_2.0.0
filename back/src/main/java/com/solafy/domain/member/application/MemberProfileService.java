package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberFindDao;
import com.solafy.domain.member.dto.MemberProfileUpdate;
import com.solafy.domain.member.entity.Member;
import com.solafy.domain.member.exception.EmailInvalidValueException;
import com.solafy.domain.member.exception.RoleWrongException;
import com.solafy.global.common.response.BasicResponse;
import com.solafy.global.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProfileService {

    private final MemberFindDao memberFindDao;
    private final PasswordEncoder passwordEncoder;
    private final RegexChecker regexChecker;

    public void updateRole(final Long id){
        final Member member = memberFindDao.findById(id);

        if(!member.getRole().equals("ROLE_WAIT")){
            throw new RoleWrongException();
        }

        member.updateRole();
    }

    public void updateProfile(final Long id, final MemberProfileUpdate dto){
        final Member member = memberFindDao.findById(id);

        if(!regexChecker.emailCheck(dto.getEmail())){
            throw new EmailInvalidValueException(dto.getEmail());
        }

        String encodePassword = "";
        if(!regexChecker.stringCheck(dto.getPassword())) {
            encodePassword = member.getPassword();
        }else{
            encodePassword = passwordEncoder.encode(dto.getPassword());
        }

        if(!regexChecker.phoneCheck(dto.getPhoneNum())){
            throw new InvalidValueException("phone number");
        }

        member.updateProfile(dto, encodePassword);
    }
}
