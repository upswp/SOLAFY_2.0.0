package com.solafy.domain.member.dto;

import com.solafy.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SignUpRequest {

    private Long nametagId;

    private Long profileId;

    private String name;

    private Long ssafy;

    private String email;

    private String password;

    private String phoneNum;

    SignUpRequest(Long nametagId, Long profileId, String name, Long ssafy, String email, String password, String phoneNum){
        this.nametagId = nametagId;
        this.profileId = profileId;
        this.name = name;
        this.ssafy = ssafy;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
    }

    public Member toEntity(String encodePassword, LocalDateTime nowTime){
        return Member.builder()
                .nametagId(nametagId)
                .profileId(profileId)
                .name(name)
                .ssafy(ssafy)
                .email(email)
                .password(encodePassword)
                .phoneNum(phoneNum)
                .createDate(nowTime)
                .updateDate(nowTime)
                .roles(Collections.singletonList("ROLE_WAIT"))
                .build();
    }
}
