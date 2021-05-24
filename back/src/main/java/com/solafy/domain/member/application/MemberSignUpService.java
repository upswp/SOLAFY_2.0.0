package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.SignUpRequest;
import com.solafy.domain.member.entity.Member;
import com.solafy.domain.member.exception.EmailDuplicateException;
import com.solafy.domain.member.exception.EmailInvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSignUpService {

    private final MemberRepository memberRepository;
    private final RegexChecker regexChecker;
    private final PasswordEncoder passwordEncoder;

    public Member doSignUp(final SignUpRequest dto) {
        final String email = dto.getEmail();

        if(!regexChecker.emailCheck(email)){
            throw new EmailInvalidValueException(email);
        }

        if (memberRepository.existsByEmail(email)) {
            throw new EmailDuplicateException(email);
        }

        final String encodePassword = passwordEncoder.encode(dto.getPassword());
        final LocalDateTime nowTime = LocalDateTime.now();
        return memberRepository.save(dto.toEntity(encodePassword, nowTime));
    }
}
