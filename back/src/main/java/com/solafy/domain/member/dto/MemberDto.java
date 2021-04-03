package com.solafy.domain.member.dto;

import com.solafy.domain.member.entity.Member;
import lombok.*;

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
