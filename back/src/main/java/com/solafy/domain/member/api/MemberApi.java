package com.solafy.domain.member.api;

import com.solafy.domain.member.application.*;
import com.solafy.domain.member.dao.MemberFindDao;
import com.solafy.domain.member.dto.MemberUpdateRequest;
import com.solafy.domain.member.dto.MemberResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2. Member"})
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberFindDao memberFindDao;
    private final MemberSearchService memberSearchService;
    private final MemberUpdateService memberUpdateService;

    @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "모든 회원 목록 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "")
    public List<MemberResponse> findAllMember() {
        return memberSearchService.findByAll();
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "승인 회원정보", notes = "승인 회원정보 상세보기.")
    @GetMapping("/{memberNo}")
    public MemberResponse getMember(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) final Long memberNo) {
        return new MemberResponse(memberFindDao.findByIdAndRole(memberNo, "ROLE_USER"));
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "미승인 회원정보", notes = "미승인 회원정보 상세보기.")
    @GetMapping(value = "/unapproved/{memberNo}")
    public MemberResponse getUnapproved(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) final Long memberNo) {
        return new MemberResponse(memberFindDao.findByIdAndRole(memberNo, "ROLE_WAIT"));
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "승인된 회원 목록 조회", notes = "모든 승인된 회원을 조회한다")
    @GetMapping(value = "/list/{pageNo}")
    public List<MemberResponse> getMemberList(@ApiParam(value = "페이지번호", required = true) @PathVariable(required = true) int pageNo,
                                    @ApiParam(value = "정렬기준") @RequestParam(required = false) String sort,
                                    @ApiParam(value = "검색타입") @RequestParam(required = false) String type,
                                    @ApiParam(value = "검색키워드") @RequestParam(required = false) String keyword) {
        return memberSearchService.getApprovedMemberList(pageNo,sort,type,keyword);
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "미승인된 회원 목록 조회", notes = "모든 미승인된 회원을 조회한다")
    @GetMapping(value = "/unapproved/list/{pageNo}")
    public List<MemberResponse> getUnapprovedList(@ApiParam(value = "페이지번호", required = true) @PathVariable(required = true) int pageNo,
                                        @ApiParam(value = "정렬기준") @RequestParam(required = false) String sort,
                                        @ApiParam(value = "검색타입") @RequestParam(required = false) String type,
                                        @ApiParam(value = "검색키워드") @RequestParam(required = false) String keyword) {
        return memberSearchService.getUnapprovedMemberList(pageNo,sort,type,keyword);
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "회원 승인", notes = "회원을 승인한다.")
    @PutMapping(value = "/admin/{memberNo}")
    public void updateRole(@ApiParam(value = "회원번호", required = true) @PathVariable long memberNo) {
        memberUpdateService.updateRole(memberNo);
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "회원 정보 업데이트", notes = "회원 정보를 수정합니다.")
    @PutMapping(value = "/{memberNo}")
    public void updateProfile(@ApiParam(value = "회원번호", required = true) @PathVariable(required = true) final Long memberNo,
                             @RequestBody final MemberUpdateRequest dto) {
        memberUpdateService.updateProfile(memberNo, dto);
    }
}