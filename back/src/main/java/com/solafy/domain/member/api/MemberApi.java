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
public class MemberApi {


}