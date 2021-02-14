package com.solafy.controller;

import com.solafy.entity.Member;
import com.solafy.model.BasicResponse;
import com.solafy.model.MailDto;
import com.solafy.model.MemberDto;
import com.solafy.repo.MemberRepository;
import com.solafy.service.EmailService;
import com.solafy.service.RegexChecker;
import com.solafy.service.SortService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @ApiOperation(value = "회원 목록 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "")
    public BasicResponse findAllMember() {
        BasicResponse result = new BasicResponse("success");
        List<Member> mlist = memberRepository.findAll();
        List<MemberDto> list = new ArrayList<>();
        for(int i = 0; i < mlist.size(); i++){
            MemberDto memberDto = new MemberDto(mlist.get(i));
            list.add(memberDto);
        }
        result.message = "회원 목록을 반환합니다.";
        result.data = list;
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "승인 회원정보", notes = "승인 회원정보 상세보기.")
    @GetMapping(value = "/{memberNo}")
    public BasicResponse memberDetail(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) long memberNo) {
        BasicResponse result = new BasicResponse("success");
        Member member = memberRepository.findByMemberNo(memberNo);
        if(member == null) {
            result.status = "error";
            result.message = "잘못된 회원번호입니다.";
            return result;
        }else if(member.getRole().equals("ROLE_WAIT")){
            result.status = "error";
            result.message = "이 회원은 미승인된 회원입니다.";
            return result;
        }
        MemberDto memberDto = new MemberDto(member);
        result.message = "회원 상세정보 조회를 성공했습니다.";
        result.data = memberDto;

        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "미승인 회원정보", notes = "미승인 회원정보 상세보기.")
    @GetMapping(value = "/unapproved/{memberNo}")
    public BasicResponse unapprovedDetail(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) long memberNo) {
        BasicResponse result = new BasicResponse("success");
        Member member = memberRepository.findByMemberNo(memberNo);
        if(member == null) {
            result.status = "error";
            result.message = "잘못된 회원번호입니다.";
            return result;
        }else if(!member.getRole().equals("ROLE_WAIT")){
            result.status = "error";
            result.message = "이 회원은 승인된 회원입니다.";
            return result;
        }
        MemberDto memberDto = new MemberDto(member);
        result.message = "회원 상세정보 조회를 성공했습니다.";
        result.data = memberDto;

        return result;
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
        BasicResponse result = new BasicResponse("success");

        List<Member> mlist = sortService.sortMember(pageNo,sort,type,keyword,"approved");

        List<MemberDto> list = new ArrayList<>();
        for(int i = 0; i < mlist.size(); i++){
            MemberDto memberDto = new MemberDto(mlist.get(i));
            list.add(memberDto);
        }
        result.message = "승인된 회원 목록을 반환합니다.";
        if(list.size() == 0) result.message = "승인된 회원 목록이 없습니다.";
        result.data = list;
        return result;
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
        BasicResponse result = new BasicResponse("success");

        List<Member> mlist = sortService.sortMember(pageNo,sort,type,keyword,"unapproved");

        List<MemberDto> list = new ArrayList<>();
        for(int i = 0; i < mlist.size(); i++){
            MemberDto memberDto = new MemberDto(mlist.get(i));
            list.add(memberDto);
        }
        result.message = "미승인 회원 목록을 반환합니다.";
        if(list.size() == 0) result.message = "미승인된 회원 목록이 없습니다.";
        result.data = list;
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다.")
    @DeleteMapping(value = "/{memberNo}")
    public BasicResponse deleteMember(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) long memberNo) {
        BasicResponse result = new BasicResponse("success");
        Member member = memberRepository.findByMemberNo(memberNo);
        if(member == null) {
            result.status = "error";
            result.message = "잘못된 회원번호입니다.";
            return result;
        }
        memberRepository.deleteByMemberNo(memberNo);
        member = memberRepository.findByMemberNo(memberNo);
        if(member != null) {
            result.status = "error";
            result.message = "회원탈퇴에 실패하였습니다.";
            return result;
        }
        result.message = "회원탈퇴에 성공하였습니다.";

        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 정보 업데이트", notes = "회원 정보를 수정합니다.")
    @PutMapping(value = "")
    public BasicResponse updateMember(@RequestBody MemberDto memberDto) {
        BasicResponse result = new BasicResponse("success");
        long memberNo = memberDto.getMemberNo();
        Member memberObj = memberRepository.findByMemberNo(memberNo);
        if(memberObj == null){
            result.status = "error";
            result.message = "잘못된 회원번호입니다.";
            result.data = false;
            return result;
        }
        long nametagId = memberDto.getNametagId();
        long profileId = memberDto.getProfileId();
        String name = memberDto.getName();
        if(name == null || name.equals("")){
            result.status = "error";
            result.message = "이름을 입력하세요.";
            result.data = false;
            return result;
        }
        long ssafy = memberDto.getSsafy();
        if(ssafy < 1){
            result.status = "error";
            result.message = "기수를 입력하세요.";
            result.data = false;
            return result;
        }
        String email = memberDto.getEmail();
        if(!regexChecker.emailCheck(email)){
            result.status = "error";
            result.message = "이메일 형식이 잘못되었습니다.";
            result.data = false;
            return result;
        }
        String password = memberDto.getPassword();
        if(password == null || password.equals("")) password = memberObj.getPassword();
        String phoneNum = memberDto.getPhoneNum();
        if(phoneNum == null || phoneNum.equals("")){
            result.status = "error";
            result.message = "전화번호를 입력하세요.";
            result.data = false;
            return result;
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
            result.status = "error";
            result.message = "권한 변경에 실패하였습니다.";
            result.data = false;
            return result;
        }
        result.message = "권한 변경에 성공하였습니다.";
        result.data = true;

        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 승인", notes = "회원을 승인한다.")
    @PutMapping(value = "/{memberNo}")
    public BasicResponse updateMemberRole(@ApiParam(value = "회원번호", required = true) @PathVariable long memberNo) {
        BasicResponse result = new BasicResponse("success");
        Member memberObj = memberRepository.findByMemberNo(memberNo);
        if(memberObj == null){
            result.status = "error";
            result.message = "잘못된 회원번호입니다.";
            result.data = false;
            return result;
        }
        String memberRole = memberObj.getRole();
        if(!memberRole.equals("ROLE_WAIT")){
            result.status = "error";
            result.message = "권한을 변경할 필요가 없는 회원입니다.";
            result.data = false;
            return result;
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
            result.status = "error";
            result.message = "권한 변경에 실패하였습니다.";
            result.data = false;
            return result;
        }
        result.message = "권한 변경에 성공하였습니다.";
        result.data = true;

        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "이메일 발송 ", notes = "이메일을 발송한다")
    @GetMapping(value = "/emailsend")
    public BasicResponse updateRole(@ApiParam(value = "이메일", required = true) @RequestParam String email) {
        BasicResponse result = new BasicResponse("success");
        MailDto mailDto = new MailDto();
        mailDto.setAddress(email);
        mailDto.setTitle("타이틀");
        mailDto.setMessage("메시지");
        mailService.mailSend(mailDto);

        return result;
    }

}