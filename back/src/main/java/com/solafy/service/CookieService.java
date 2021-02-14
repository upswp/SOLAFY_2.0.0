package com.solafy.service;

import com.solafy.security.model.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    HttpServletResponse httpServletResponse;

    @Autowired
    SecurityProperties securityProperties;

    public Cookie getCookie(String name){
        return WebUtils.getCookie(httpServletRequest, name);
    }

    public void setCookie(String name, String value, int expiryIndays){
        int expiresInSeconds = (int) TimeUnit.DAYS.toSeconds(expiryIndays);
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(securityProperties.getCookieProps().isSecure());
        cookie.setPath(securityProperties.getCookieProps().getPath());
        cookie.setDomain(securityProperties.getCookieProps().getDomain());
        cookie.setMaxAge(expiresInSeconds);
        httpServletResponse.addCookie(cookie);
    }

    public void setSecureCookie(String name, String value, int expiryInDays) {
        int expiresInSeconds = (int) TimeUnit.DAYS.toSeconds(expiryInDays);
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(securityProperties.getCookieProps().isHttpOnly());
        cookie.setSecure(securityProperties.getCookieProps().isSecure());
        cookie.setPath(securityProperties.getCookieProps().getPath());
        cookie.setDomain(securityProperties.getCookieProps().getDomain());
        cookie.setMaxAge(expiresInSeconds);
        httpServletResponse.addCookie(cookie);
    }

    public void setSecureCookie(String name, String value) {
        int expiresInMinutes = securityProperties.getCookieProps().getMaxAge();
        setSecureCookie(name, value, expiresInMinutes);
    }

    public void deleteSecureCookie(String name) {
        int expiresInSeconds = 0;
        Cookie cookie = new Cookie(name, null);
        cookie.setHttpOnly(securityProperties.getCookieProps().isHttpOnly());
        cookie.setSecure(securityProperties.getCookieProps().isSecure());
        cookie.setPath(securityProperties.getCookieProps().getPath());
        cookie.setDomain(securityProperties.getCookieProps().getDomain());
        cookie.setMaxAge(expiresInSeconds);
        httpServletResponse.addCookie(cookie);
    }

    public void deleteCookie(String name) {
        int expiresInSeconds = 0;
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(securityProperties.getCookieProps().getPath());
        cookie.setDomain(securityProperties.getCookieProps().getDomain());
        cookie.setMaxAge(expiresInSeconds);
        httpServletResponse.addCookie(cookie);
    }
}
