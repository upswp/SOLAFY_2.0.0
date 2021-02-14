package com.solafy.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexChecker {
//    public boolean isValidEmail(String email){
//        boolean err = false;
//        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(email);
//        if(m.matches()) {
//            err = true;
//        }
//        return err;
//    }
    public boolean emailCheck(String email) {
        if(email == null) return false;
        String EMAIL_REGEX = "^[_a-z0-9-]+(._a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Boolean b = email.matches(EMAIL_REGEX);
        return b;
    }
}
