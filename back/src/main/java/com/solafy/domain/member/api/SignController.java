package com.solafy.domain.member.api;

import com.solafy.domain.member.application.RegexChecker;
import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.FindPasswordDto;
import com.solafy.domain.member.dto.LoginMember;
import com.solafy.domain.member.dto.SignUpMember;
import com.solafy.domain.member.entity.Member;
import com.solafy.global.common.response.BasicResponse;
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

//@Api(tags = {"1. Sign"})
//@RequiredArgsConstructor
//@RestController
//@RequestMapping(value = "/members")
public class SignController {

//    @Autowired
//    private final MemberRepository memberRepository;
//    @Autowired
//    private final JwtTokenProvider jwtTokenProvider;
//    @Autowired
//    private final PasswordEncoder passwordEncoder;
//    @Autowired
//    private final RegexChecker regexChecker;
//
//    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
//    @PostMapping(value = "/login")
//    public BasicResponse loginMember(@RequestBody LoginMember loginMember) {
//        String email = loginMember.getEmail();
//        if(!regexChecker.emailCheck(email)){
//            return new BasicResponse("error","이메일 형식이 잘못되었습니다.",null);
//        }
//        Member member = memberRepository.findByEmail(email).get();
//        if(member == null){
//            return new BasicResponse("error","가입되지 않은 이메일입니다.",null);
//        }
//        String password = loginMember.getPassword();
//        if (!passwordEncoder.matches(password, member.getPassword())) {
//            return new BasicResponse("error","비밀번호가 틀렸습니다.",null);
//        }
//        String token = jwtTokenProvider.createToken(String.valueOf(member.getMemberNo()), member.getRoles());
//        return new BasicResponse("success","로그인에 성공하였습니다.",token);
//    }
//
//    @ApiOperation(value = "비밀번호 변경 요청", notes = "비밀번호 변경 요청을 한다.")
//    @PostMapping(value = "/password")
//    public BasicResponse findPassword(@RequestBody FindPasswordDto findPasswordDto) {
//        int count = findPasswordDto.getCount();
//        if(count >= 5){
//            return new BasicResponse("error","비밀번호 변경 요청을 5회 이상 실패했습니다.",null);
//        }
//        String email = findPasswordDto.getEmail();
//        if(!regexChecker.emailCheck(email)){
//            return new BasicResponse("error","이메일 형식이 잘못되었습니다.",null);
//        }
//        Member member = memberRepository.findByEmail(email).get();
//        if(member == null){
//            return new BasicResponse("error","이메일이 잘못되었습니다.",null);
//        }
//        if(!member.getName().equals(findPasswordDto.getName())){
//            return new BasicResponse("error","이름이 일치하지 않습니다.",null);
//        }
//        if(member.getSsafy() != findPasswordDto.getSsafy()){
//            return new BasicResponse("error","기수가 일치하지 않습니다.",null);
//        }
//        return new BasicResponse("success","비밀번호 요청에 성공하였습니다.",null);
//    }
//
//    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
//    @PostMapping(value = "/signup")
//    public BasicResponse createMember(@RequestBody SignUpMember signupMember) {
//        long nametagId = signupMember.getNametagId();
//        long profileId = signupMember.getProfileId();
//        String name = signupMember.getName();
//        long ssafy = signupMember.getSsafy();
//        String email = signupMember.getEmail();
//        if(!regexChecker.emailCheck(email)){
//            return new BasicResponse("error","이메일 형식이 잘못되었습니다.",null);
//        }
//        String password = signupMember.getPassword();
//        String phoneNum = signupMember.getPhoneNum();
//        LocalDateTime time = LocalDateTime.now();
//        Member member = memberRepository.save(Member.builder()
//                .nametagId(nametagId)
//                .profileId(profileId)
//                .name(name)
//                .ssafy(ssafy)
//                .email(email)
//                .password(passwordEncoder.encode(password))
//                .phoneNum(phoneNum)
//                .createDate(time)
//                .updateDate(time)
//                .roles(Collections.singletonList("ROLE_WAIT"))
//                .build());
//        if(member == null) {
//            return new BasicResponse("error","회원가입에 실패하였습니다.",null);
//        }
//        return new BasicResponse("success","회원가입에 성공하였습니다.",null);
//    }
//
//    @ApiOperation(value = "이메일 중복 확인", notes = "중복된 이메일이 있는지 확인합니다.")
//    @GetMapping(value = "/email")
//    public BasicResponse checkEmail(@ApiParam(value = "이메일", required = true) @RequestParam(required = true) String email) {
//        if(!regexChecker.emailCheck(email)){
//            return new BasicResponse("error","이메일 형식이 잘못되었습니다.",false);
//        }
//        Member member = memberRepository.findByEmail(email).get();
//        if(member != null) {
//            return new BasicResponse("error","사용 불가능한 이메일입니다.",false);
//        }
//        return new BasicResponse("success","사용 가능한 이메일입니다.",true);
//    }
}