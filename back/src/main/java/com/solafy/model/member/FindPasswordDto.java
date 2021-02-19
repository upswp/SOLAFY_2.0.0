package com.solafy.model.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FindPasswordDto {
    private int count; // 비밀번호 틀린 횟수
    private String name;
    private long ssafy;
    private String email;
}
