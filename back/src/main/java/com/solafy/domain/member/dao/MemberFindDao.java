package com.solafy.domain.member.dao;

import com.solafy.domain.member.application.MemberResponseService;
import com.solafy.domain.member.application.RegexChecker;
import com.solafy.domain.member.dto.MemberResponse;
import com.solafy.domain.member.entity.Member;
import com.solafy.domain.member.exception.EmailInvalidValueException;
import com.solafy.domain.member.exception.EmailNotFoundException;
import com.solafy.domain.member.exception.MemberNotFoundException;
import com.solafy.domain.member.exception.RoleWrongException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFindDao {

    private final MemberRepository memberRepository;
    private final RegexChecker regexChecker;

    public Member findById(final Long id) {
        final Optional<Member> member = memberRepository.findById(id);
        member.orElseThrow(() -> new MemberNotFoundException(id));
        return member.get();
    }

    public Member findByIdAndRole(final Long id, final String role){
        final Member member = findById(id);
        if(!member.getRole().equals(role)){
           throw new RoleWrongException();
        }
        return member;
    }

    public Member findByEmail(final String email) {
        if(!regexChecker.emailCheck(email)){
            throw new EmailInvalidValueException(email);
        }
        final Optional<Member> member = memberRepository.findByEmail(email);
        member.orElseThrow(() -> new EmailNotFoundException(email));
        return member.get();
    }
}
