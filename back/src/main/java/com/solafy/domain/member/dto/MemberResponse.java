package com.solafy.domain.member.dto;

import com.solafy.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberResponse {

    private Long memberNo;

    private Long nametagId;

    private Long profileId;

    private String name;

    private Long ssafy;

    private String email;

    private String phoneNum;

    private String memberRole;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public MemberResponse(Member member){
        this.memberNo = member.getMemberNo();
        if(member.getNametagId() != null){
            this.nametagId = member.getNametagId();
        }
        if(member.getProfileId() != null){
            this.profileId = member.getProfileId();
        }
        this.name = member.getName();
        this.ssafy = member.getSsafy();
        this.email = member.getEmail();
        this.phoneNum = member.getPhoneNum();
        this.memberRole = member.getRole();
        this.createDate = member.getCreateDate();
        this.updateDate = member.getUpdateDate();
    }
}
