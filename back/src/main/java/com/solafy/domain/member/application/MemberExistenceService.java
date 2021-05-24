package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.MemberExistenceType;
import com.solafy.domain.member.entity.Member;
import com.solafy.global.common.response.Existence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberExistenceService {

    private final MemberRepository memberRepository;

    public Boolean isExistTarget(final MemberExistenceType type, final String value) {
        Boolean isExist = false;

        switch (type) {
            case EMAIL:
                isExist =  memberRepository.existsByEmail(value);
                break;
            default:
                throw new IllegalArgumentException(String.format("%s is not valid", type.name()));
        }

        return isExist;
    }
}
