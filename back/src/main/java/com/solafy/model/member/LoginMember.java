package com.solafy.model.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginMember {
    private String email;
    private String password;
}
