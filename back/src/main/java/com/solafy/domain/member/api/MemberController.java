package com.solafy.domain.member.api;

import com.solafy.domain.member.application.EmailService;
import com.solafy.domain.member.application.RegexChecker;
import com.solafy.domain.member.application.SortService;
import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.MemberDto;
import com.solafy.domain.member.entity.Member;
import com.solafy.global.common.response.BasicResponse;
import com.solafy.infra.sms.dto.MailDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Api(tags = {"2. Member"})
@RequiredArgsConstructor
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final EmailService mailService;
    @Autowired
    private final SortService sortService;
    @Autowired
    private final RegexChecker regexChecker;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "모든 회원 목록 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "")
    public BasicResponse findAllMember() {
        List<Member> mList = memberRepository.findAll();
        List<MemberDto> list = sortService.convertMemberToDto(mList);
        return new BasicResponse("success","전체 회원 목록을 반환합니다.",list);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "승인 회원정보", notes = "승인 회원정보 상세보기.")
    @GetMapping(value = "/{memberNo}")
    public BasicResponse memberDetail(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) long memberNo) {
        Member member = memberRepository.findByMemberNo(memberNo);
        if(member == null) {
            return new BasicResponse("error","잘못된 회원번호입니다.",null);
        }else if(member.getRole().equals("ROLE_WAIT")){
            return new BasicResponse("error","이 회원은 미승인된 회원입니다.",null);
        }
        MemberDto memberDto = new MemberDto(member);
        return new BasicResponse("success","회원 상세정보 조회를 성공했습니다.",memberDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "미승인 회원정보", notes = "미승인 회원정보 상세보기.")
    @GetMapping(value = "/unapproved/{memberNo}")
    public BasicResponse unapprovedDetail(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) long memberNo) {
        Member member = memberRepository.findByMemberNo(memberNo);
        if(member == null) {
            return new BasicResponse("error","잘못된 회원번호입니다.",null);
        }else if(!member.getRole().equals("ROLE_WAIT")){
            return new BasicResponse("error","이 회원은 승인된 회원입니다.",null);
        }
        MemberDto memberDto = new MemberDto(member);
        return new BasicResponse("success","회원 상세정보 조회를 성공했습니다.",memberDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "승인된 회원 목록 조회", notes = "모든 승인된 회원을 조회한다")
    @GetMapping(value = "/list/{pageNo}")
    public BasicResponse memberList(@ApiParam(value = "페이지번호", required = true) @PathVariable(required = true) int pageNo,
                                    @ApiParam(value = "정렬기준") @RequestParam(required = false) String sort,
                                    @ApiParam(value = "검색타입") @RequestParam(required = false) String type,
                                    @ApiParam(value = "검색키워드") @RequestParam(required = false) String keyword) {
        List<Member> mList = sortService.sortMember(pageNo,sort,type,keyword,"approved");
        List<MemberDto> list = sortService.convertMemberToDto(mList);
        if(list.size() == 0) return new BasicResponse("success","조건에 맞는 승인된 회원이 없습니다.",null);
        else return new BasicResponse("success","승인 회원 목록을 반환합니다.",list);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "미승인된 회원 목록 조회", notes = "모든 미승인된 회원을 조회한다")
    @GetMapping(value = "/unapproved/list/{pageNo}")
    public BasicResponse unapprovedList(@ApiParam(value = "페이지번호", required = true) @PathVariable(required = true) int pageNo,
                                        @ApiParam(value = "정렬기준") @RequestParam(required = false) String sort,
                                        @ApiParam(value = "검색타입") @RequestParam(required = false) String type,
                                        @ApiParam(value = "검색키워드") @RequestParam(required = false) String keyword) {
        List<Member> mList = sortService.sortMember(pageNo,sort,type,keyword,"unapproved");
        List<MemberDto> list = sortService.convertMemberToDto(mList);
        if(list.size() == 0) return new BasicResponse("success","조건에 맞는 미승인된 회원이 없습니다.",null);
        else return new BasicResponse("success","미승인 회원 목록을 반환합니다.",list);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다.")
    @DeleteMapping(value = "/{memberNo}")
    public BasicResponse deleteMember(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) long memberNo) {
        Member member = memberRepository.findByMemberNo(memberNo);
        if(member == null) {
            return new BasicResponse("error","잘못된 회원번호입니다.",null);
        }
        memberRepository.deleteByMemberNo(memberNo);
        member = memberRepository.findByMemberNo(memberNo);
        if(member != null) {
            return new BasicResponse("error","회원탈퇴에 실패하였습니다",null);
        }
        return new BasicResponse("success","회원탈퇴에 성공하였습니다.",null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 정보 업데이트", notes = "회원 정보를 수정합니다.")
    @PutMapping(value = "")
    public BasicResponse updateMember(@RequestBody MemberDto memberDto) {
        long memberNo = memberDto.getMemberNo();
        Member memberObj = memberRepository.findByMemberNo(memberNo);
        if(memberObj == null){
            return new BasicResponse("error","잘못된 회원번호입니다.",null);
        }
        long nametagId = memberDto.getNametagId();
        long profileId = memberDto.getProfileId();
        String name = memberDto.getName();
        if(!regexChecker.stringCheck(name)){
            return new BasicResponse("error","이름을 입력하세요.",null);
        }
        long ssafy = memberDto.getSsafy();
        if(ssafy < 1){
            return new BasicResponse("error","기수를 입력하세요.",null);
        }
        String email = memberDto.getEmail();
        if(!regexChecker.emailCheck(email)){
            return new BasicResponse("error","이메일 형식이 잘못되었습니다.",null);
        }
        String password = memberDto.getPassword();
        if(!regexChecker.stringCheck(password)) password = memberObj.getPassword();
        String phoneNum = memberDto.getPhoneNum();
        if(!regexChecker.phoneCheck(phoneNum)){
            return new BasicResponse("error","전화번호 형식이 잘못되었습니다.",null);
        }
        LocalDateTime createDate = memberObj.getCreateDate();
        String role = memberObj.getRole();
        LocalDateTime time = LocalDateTime.now();
        Member member = memberRepository.save(Member.builder()
                .memberNo(memberNo)
                .nametagId(nametagId)
                .profileId(profileId)
                .name(name)
                .ssafy(ssafy)
                .email(email)
                .password(password)
                .phoneNum(phoneNum)
                .createDate(createDate)
                .updateDate(time)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        if(member == null) {
            return new BasicResponse("error","회원정보 수정에 실패하였습니다.",null);
        }
        return new BasicResponse("success","회원정보 수정에 성공하였습니다.",memberNo);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 승인", notes = "회원을 승인한다.")
    @PutMapping(value = "/admin/{memberNo}")
    public BasicResponse updateMemberRole(@ApiParam(value = "회원번호", required = true) @PathVariable long memberNo) {
        Member memberObj = memberRepository.findByMemberNo(memberNo);
        if(memberObj == null){
            return new BasicResponse("error","잘못된 회원번호입니다.",false);
        }
        String memberRole = memberObj.getRole();
        if(!memberRole.equals("ROLE_WAIT")){
            return new BasicResponse("error","권한을 변경할 필요가 없는 회원입니다.",false);
        }
        String name = memberObj.getName();
        long ssafy = memberObj.getSsafy();
        String email = memberObj.getEmail();
        String password = memberObj.getPassword();
        String phoneNum = memberObj.getPhoneNum();
        LocalDateTime createDate = memberObj.getCreateDate();
        LocalDateTime time = LocalDateTime.now();
        Member member = memberRepository.save(Member.builder()
                .memberNo(memberNo)
                .name(name)
                .ssafy(ssafy)
                .email(email)
                .password(password)
                .phoneNum(phoneNum)
                .createDate(createDate)
                .updateDate(time)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        if(member == null) {
            return new BasicResponse("error","권한 변경에 실패하였습니다.",false);
        }
        return new BasicResponse("success","권한 변경에 성공하였습니다.",true);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "이메일 발송 ", notes = "이메일을 발송한다")
    @GetMapping(value = "/emailsend")
    public BasicResponse updateRole(@ApiParam(value = "이메일", required = true) @RequestParam String email) {
        Member member = memberRepository.findByEmail(email).get();
        if(member == null){
            return new BasicResponse("error","가입되지 않은 이메일입니다.",null);
        }
        MailDto mailDto = new MailDto(email,"타이틀","메시지");
        mailService.mailSend(mailDto);
        return new BasicResponse("success","이메일을 발송하였습니다.",null);
    }

}