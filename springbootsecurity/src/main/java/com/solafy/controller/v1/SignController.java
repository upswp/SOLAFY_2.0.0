package com.solafy.controller.v1;

import com.solafy.config.JwtTokenProvider;
import com.solafy.entity.Member;
import com.solafy.model.BasicResponse;
import com.solafy.repo.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Basic;
import java.util.Collections;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public BasicResponse signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String email,
                                @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {
        BasicResponse result = new BasicResponse("success");
        Member member = memberRepository.findByEmail(email);
        if (!passwordEncoder.matches(password, member.getPassword())) {
            result.status = "error";
            result.message = "비밀번호가 틀렸습니다.";
            return result;
        }
        result.message = "로그인에 성공했습니다.";
        String token = jwtTokenProvider.createToken(String.valueOf(member.getMemberNo()), member.getRoles());
        result.data = token;

        return result;

    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public BasicResponse signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String email,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                               @ApiParam(value = "이름", required = true) @RequestParam String name) {
        BasicResponse result = new BasicResponse("success");
        Member member = memberRepository.save(Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        if(member == null) {
            result.status = "error";
            result.message = "가입에 실패했습니다.";
        }
        result.message = "가입에 성공했습니다.";

        return result;
    }
}