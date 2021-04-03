package com.solafy.domain.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpMember {

    private long nametagId;

    private long profileId;

    private String name;

    private long ssafy;

    private String email;

    private String password;

    private String phoneNum;
}
