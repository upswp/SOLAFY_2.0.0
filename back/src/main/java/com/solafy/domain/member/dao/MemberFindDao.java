package com.solafy.domain.member.dao;

import com.solafy.domain.member.entity.Member;
import com.solafy.domain.member.exception.EmailNotFoundException;
import com.solafy.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFindDao {

    private final MemberRepository memberRepository;

    public Member findById(final Long id) {
        final Optional<Member> member = memberRepository.findById(id);
        member.orElseThrow(() -> new MemberNotFoundException(id));
        return member.get();
    }

    public Member findByEmail(final String email) {
        final Optional<Member> member = memberRepository.findByEmail(email);
        member.orElseThrow(() -> new EmailNotFoundException(email));
        return member.get();
    }
}
