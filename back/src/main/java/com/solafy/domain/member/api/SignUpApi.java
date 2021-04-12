package com.solafy.domain.member.api;

import com.solafy.domain.member.application.*;
import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.*;
import com.solafy.domain.member.entity.Member;
import com.solafy.global.common.response.Existence;
import com.solafy.global.common.response.Token;
import com.solafy.global.config.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@Api(tags = {"1. Sign"})
@RestController
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class SignUpApi {

    private final MemberLoginService memberLoginService;
    private final MemberSignUpService memberSignUpService;
    private final MemberSearchService memberSearchService;
    private final MemberFindPasswordService memberFindPasswordService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/login")
    public Token loginMember(@RequestBody LoginMember dto) {
        return new Token(memberLoginService.doLogin(dto));
    }

    @ApiOperation(value = "비밀번호 변경 요청", notes = "비밀번호 변경 요청을 한다.")
    @PostMapping(value = "/password")
    public Existence findPassword(@RequestBody final FindPasswordDto dto) {
        return new Existence(memberFindPasswordService.findPassword(dto));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public MemberResponse createMember(@RequestBody final SignUpRequest dto) {
        final Member member = memberSignUpService.doSignUp(dto);
        return new MemberResponse(member);
    }

    @ApiOperation(value = "중복 확인", notes = "해당 멤버가 있는지 확인합니다.")
    @GetMapping(value = "/existence")
    public Existence isExistTarget(@RequestParam("type") final MemberExistenceType type,
                                   @RequestParam(value = "value", required = false) final String value){
        return new Existence(memberSearchService.isExistTarget(type, value));
    }
}