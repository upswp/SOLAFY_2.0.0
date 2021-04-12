package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.MemberExistenceType;
import com.solafy.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSearchService{

    private final MemberRepository memberRepository;

    public boolean isExistTarget(final MemberExistenceType type, final String value) {
        Optional<Member> memberObj = null;

        switch (type) {
            case EMAIL:
                memberObj = memberRepository.findByEmail(value);
                break;
            default:
                throw new IllegalArgumentException(String.format("%s is not valid", type.name()));
        }

        final Member member = memberObj.get();
        return member != null;
    }
}
