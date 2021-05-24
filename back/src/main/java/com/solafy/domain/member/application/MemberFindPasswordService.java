package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberFindDao;
import com.solafy.domain.member.dto.FindPasswordRequest;
import com.solafy.domain.member.entity.Member;
import com.solafy.domain.member.exception.FailedCountOverException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFindPasswordService {

    private final MemberFindDao memberFindDao;

    public Boolean findPassword(final FindPasswordRequest dto){
        if(dto.getCount() >= 5){
            throw new FailedCountOverException();
        }

        return isInfoMatch(dto);
    }

    public Boolean isInfoMatch(final FindPasswordRequest dto){
        final Member member = memberFindDao.findByEmail(dto.getEmail());

        if(!member.getName().equals(dto.getName())){
            return false;
        }
        if(member.getSsafy() != dto.getSsafy()){
            return false;
        }

        return true;
    }
}
