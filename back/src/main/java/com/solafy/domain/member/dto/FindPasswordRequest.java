package com.solafy.domain.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FindPasswordRequest {

    private String name;

    private Long ssafy;

    private String email;

    private int count; // 비밀번호 틀린 횟수
}
