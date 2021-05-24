package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberFindDao;
import com.solafy.domain.member.dto.LoginRequest;
import com.solafy.domain.member.entity.Member;
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

    public String doLogin(final LoginRequest dto){
        final Member member = memberFindDao.findByEmail(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new LoginInputInvalidException();
        }

        final String token = jwtTokenProvider.createToken(String.valueOf(member.getMemberNo()), member.getRoles());

        return token;
    }
}
