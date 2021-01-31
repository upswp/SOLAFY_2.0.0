package com.solafy.model.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Member {
    @Id
    @GeneratedValue
    private Long no;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    @NotNull
    private int generation;
    @NotBlank
    private String phone;
    @NotBlank
    private String nameTag;
    private String profileImg;
    @CreationTimestamp
    private Date createDate;
    @CreationTimestamp
    private Date updateDate;


    public Member() {}
    @Builder

    public Member(@NotBlank String name, @NotBlank @Email String email, @NotBlank String nickname, @NotBlank String password, @NotNull int generation, @NotBlank String phone, @NotBlank String nameTag, String profileImg) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.generation = generation;
        this.phone = phone;
        this.nameTag = nameTag;
        this.profileImg = profileImg;
    }
}
