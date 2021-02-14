package com.solafy.model;

import com.solafy.entity.Member;
import lombok.*;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private long memberNo;
    private long nametagId;
    private long profileId;
    private String name;
    private long ssafy;
    private String email;
    private String password;
    private String phoneNum;
    private String memberRole;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private int count; // 비밀번호 틀린 횟수

    public MemberDto(Member member){
        this.memberNo = member.getMemberNo();
        this.nametagId = member.getNametagId();
        this.profileId = member.getProfileId();
        this.name = member.getName();
        this.ssafy = member.getSsafy();
        this.email = member.getEmail();
        this.phoneNum = member.getPhoneNum();
        this.memberRole = member.getRole();
        this.createDate = member.getCreateDate();
        this.updateDate = member.getUpdateDate();
    }
}
