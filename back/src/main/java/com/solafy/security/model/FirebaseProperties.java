package com.solafy.security.model;

import lombok.Data;

//getter, setter, requiredargsconst, tostring, hashcode 자동 설정
@Data
public class FirebaseProperties {
    private int sessionExpiryInDays;
    private String databaseUrl;
    private boolean enableStrictServerSession;
    private boolean enableCheckSessionRevoked;
    private boolean enableLogoutEverywhere;
}
