package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberFindDao;
import com.solafy.domain.member.dto.LoginMember;
import com.solafy.domain.member.entity.Member;
import com.solafy.domain.member.exception.EmailInvalidValueException;
import com.solafy.domain.member.exception.LoginInputInvalidException;
import com.solafy.global.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberFindDao memberFindDao;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RegexChecker regexChecker;

    public String doLogin(LoginMember dto){

        final String email = dto.getEmail();
        final String password = dto.getPassword();

        if(!regexChecker.emailCheck(email)){
            throw new EmailInvalidValueException(email);
        }

        final Member member = memberFindDao.findByEmail(email);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new LoginInputInvalidException();
        }

        final String token = jwtTokenProvider.createToken(String.valueOf(member.getMemberNo()), member.getRoles());

        return token;
    }
}
