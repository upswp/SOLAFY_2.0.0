package com.solafy.controller;

import com.solafy.config.JwtTokenProvider;
import com.solafy.entity.Member;
import com.solafy.model.BasicResponse;
import com.solafy.model.MailDto;
import com.solafy.model.MemberDto;
import com.solafy.repo.MemberRepository;
import com.solafy.service.EmailService;
import com.solafy.service.RegexChecker;
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
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/members")
public class SignController {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RegexChecker regexChecker;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/login")
    public BasicResponse loginMember(@RequestBody MemberDto memberDto) {
        BasicResponse result = new BasicResponse("success");
        String email = memberDto.getEmail();
        if(!regexChecker.emailCheck(email)){
            result.status = "error";
            result.message = "이메일 형식이 잘못되었습니다.";
            return result;
        }
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            result.status = "error";
            result.message = "이메일이 잘못되었습니다.";
            return result;
        }
        String password = memberDto.getPassword();
        if (!passwordEncoder.matches(password, member.getPassword())) {
            result.status = "error";
            result.message = "비밀번호가 틀렸습니다.";
            return result;
        }
        String token = jwtTokenProvider.createToken(String.valueOf(member.getMemberNo()), member.getRoles());
        result.message = "로그인에 성공하였습니다.";
        result.data = token;

        return result;
    }

    @ApiOperation(value = "비밀번호 변경 요청", notes = "비밀번호 변경 요청을 한다.")
    @PostMapping(value = "/password")
    public BasicResponse findPassword(@RequestBody MemberDto memberDto) {
        BasicResponse result = new BasicResponse("success");
        int count = memberDto.getCount();
        if(count >= 5){
            result.status = "error";
            result.message = "비밀번호 변경 요청을 5회 이상 실패했습니다.";
            return result;
        }
        String email = memberDto.getEmail();
        if(!regexChecker.emailCheck(email)){
            result.status = "error";
            result.message = "이메일 형식이 잘못되었습니다.";
            return result;
        }
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            result.status = "error";
            result.message = "이메일이 잘못되었습니다.";
            return result;
        }
        if(!member.getName().equals(memberDto.getName())){
            result.status = "error";
            result.message = "이름이 일치하지 않습니다.";
            return result;
        }
        if(member.getSsafy() != memberDto.getSsafy()){
            result.status = "error";
            result.message = "기수가 일치하지 않습니다.";
            return result;
        }
        result.message = "비밀번호 요청에 성공하였습니다.";

        return result;
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public BasicResponse createMember(@RequestBody MemberDto memberDto) {
        BasicResponse result = new BasicResponse("success");
        long nametagId = memberDto.getNametagId();
        long profileId = memberDto.getProfileId();
        String name = memberDto.getName();
        long ssafy = memberDto.getSsafy();
        String email = memberDto.getEmail();
        if(!regexChecker.emailCheck(email)){
            result.status = "error";
            result.message = "이메일 형식이 잘못되었습니다.";
            result.data = false;
            return result;
        }
        String password = memberDto.getPassword();
        String phoneNum = memberDto.getPhoneNum();
        LocalDateTime time = LocalDateTime.now();
        Member member = memberRepository.save(Member.builder()
                .nametagId(nametagId)
                .profileId(profileId)
                .name(name)
                .ssafy(ssafy)
                .email(email)
                .password(passwordEncoder.encode(password))
                .phoneNum(phoneNum)
                .createDate(time)
                .updateDate(time)
                .roles(Collections.singletonList("ROLE_WAIT"))
                .build());
        if(member == null) {
            result.status = "error";
            result.message = "회원가입에 실패하였습니다.";
            return result;
        }
        result.message = "회원가입에 성공하였습니다.";

        return result;
    }

    @ApiOperation(value = "이메일 중복 확인", notes = "중복된 이메일이 있는지 확인합니다.")
    @GetMapping(value = "/email")
    public BasicResponse checkEmail(@ApiParam(value = "이메일", required = true) @RequestParam(required = true) String email) {
        BasicResponse result = new BasicResponse("success");
        if(!regexChecker.emailCheck(email)){
            result.status = "error";
            result.message = "이메일 형식이 잘못되었습니다.";
            result.data = false;
            return result;
        }
        Member member = memberRepository.findByEmail(email);
        if(member != null) {
            result.status = "error";
            result.message = "사용 불가능한 이메일입니다.";
            result.data = false;
            return result;
        }
        result.message = "사용 가능한 이메일입니다.";
        result.data = true;

        return result;
    }
}