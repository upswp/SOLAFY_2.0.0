package com.solafy.domain.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberUpdateRequest {

    private Long nametagId;

    private Long profileId;

    private String name;

    private Long ssafy;

    private String email;

    private String password;

    private String phoneNum;
}
