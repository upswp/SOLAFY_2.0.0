package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberFindDao;
import com.solafy.domain.member.dto.FindPasswordDto;
import com.solafy.domain.member.entity.Member;
import com.solafy.domain.member.exception.EmailInvalidValueException;
import com.solafy.domain.member.exception.FailedCountOverException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFindPasswordService {

    private final MemberFindDao memberFindDao;
    private final RegexChecker regexChecker;

    public boolean findPassword(FindPasswordDto dto){
        final int count = dto.getCount();
        final String email = dto.getEmail();

        if(count >= 5){
            throw new FailedCountOverException();
        }

        if(!regexChecker.emailCheck(email)){
            throw new EmailInvalidValueException(email);
        }

        return isInfoMatch(dto, email);
    }

    public boolean isInfoMatch(FindPasswordDto dto, String email){
        final Member member = memberFindDao.findByEmail(email);
        final String name = member.getName();
        final Long ssafy = member.getSsafy();

        if(!name.equals(dto.getName())){
            return false;
        }
        if(ssafy != dto.getSsafy()){
            return false;
        }

        return true;
    }
}
