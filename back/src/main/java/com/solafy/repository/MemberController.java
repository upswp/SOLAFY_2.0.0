package com.solafy.repository;

import com.solafy.model.Response;
import com.solafy.model.member.Member;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("member")
public class MemberController {
    static final String SUCCESS="SUCCESS";
    static final String ERROR="error";
    static final String LOGIN_OK="로그인 성공";
    static final String LOGIN_FAIL="로그인 실패";

    //로그인 기능
    public Object login(@Valid @RequestBody Member member) {
        System.out.print(member.toString());
        //return data는 nickname과 profileImg주소
        Response response= new Response(SUCCESS, LOGIN_OK, null);
        return response;
    }
}
