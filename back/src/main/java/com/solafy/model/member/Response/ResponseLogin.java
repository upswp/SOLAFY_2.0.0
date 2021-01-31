package com.solafy.model.member.Response;

public class ResponseLogin {
    private String nickname;
    private String profileImg;

    public ResponseLogin(String nickname, String profileImg) {
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}