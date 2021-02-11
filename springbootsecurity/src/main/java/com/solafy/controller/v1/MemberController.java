package com.solafy.controller.v1;

import com.solafy.entity.Member;
import com.solafy.model.BasicResponse;
import com.solafy.repo.MemberRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2. Member"})
@RequiredArgsConstructor
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/v1/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 목록 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "")
    public BasicResponse findAllMember() {
        BasicResponse result = new BasicResponse("success");
        List<Member> list = memberRepository.findAll();
        result.message = "회원 목록을 반환합니다.";
        result.data = list;
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "회원번호로 회원을 조회한다")
    @GetMapping(value = "/{memberNo}")
    public BasicResponse findMemberByNo(
            @ApiParam(value = "회원번호", required = true) @RequestParam final long memberNo) {
        BasicResponse result = new BasicResponse("success");
        Member member = memberRepository.findByMemberNo(memberNo);
        if(member == null) {
            result.status = "error";
            result.message = "회원 정보 검색에 실패했습니다.";
            return result;
        }
        result.message = "회원 정보를 반환합니다.";
        result.data = member;
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "")
    public BasicResponse modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam final long memberNo,
            @ApiParam(value = "회원이메일", required = true) @RequestParam final String email,
            @ApiParam(value = "회원이름", required = true) @RequestParam final String name) {
        BasicResponse result = new BasicResponse("success");
        Member memberObj = Member.builder()
                .memberNo(memberNo)
                .email(email)
                .name(name)
                .build();
        Member member = memberRepository.save(memberObj);
        if(member == null){
            result.status = "error";
            result.message = "회원정보 수정에 실패했습니다.";
            return result;
        }
        result.message = "회원정보 수정에 성공했습니다.";
        result.data = member;
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원번호로 회원을 삭제한다")
    @DeleteMapping(value = "/{memberNo}")
    public BasicResponse delete(
            @ApiParam(value = "회원번호", required = true) @RequestParam final long memberNo) {
        BasicResponse result = new BasicResponse("success");
        memberRepository.deleteByMemberNo(memberNo);
        if(memberRepository.findByMemberNo(memberNo) != null){
            result.status = "error";
            result.message = "회원 삭제에 실패했습니다.";
            return result;
        }
        result.message = "회원 삭제에 성공했습니다.";
        return result;
    }
}