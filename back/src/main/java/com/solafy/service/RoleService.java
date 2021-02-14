package com.solafy.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.solafy.security.model.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RoleService{
    @Autowired
    FirebaseAuth firebaseAuth;
    @Autowired
    private SecurityProperties securityProperties;

    public void addRole(String uid, String role) throws Exception{
        try{
            UserRecord user = firebaseAuth.getUser(uid);
            Map<String, Object> claims = new HashMap<>();
            user.getCustomClaims().forEach((k,v)->claims.put(k,v));
            if(securityProperties.getValidApplicationRoles().contains(role)){
                if(!claims.containsKey(role)){
                    claims.put(role.toLowerCase(), true);
                }
                firebaseAuth.setCustomUserClaims(uid, claims);
            }
            else {
                throw new Exception("Not a vaild Application role : " +
                        securityProperties.getValidApplicationRoles().toString());
            }
        }
        catch (FirebaseAuthException e){
            log.error("Firebase Auth Error", e);
        }
    }
    public void removeRole(String uid, String role){
        try{
            UserRecord user = firebaseAuth.getUser(uid);
            Map<String, Object> claims = new HashMap<>();
            user.getCustomClaims().forEach((k,v)->claims.put(k,v));
            if(claims.containsKey(role)){
                claims.remove(role);
            }
        }
        catch (FirebaseAuthException e){
            log.error("Firebase Auth Error", e);
        }

    }
}
