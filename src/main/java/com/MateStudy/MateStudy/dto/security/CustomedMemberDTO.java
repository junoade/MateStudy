package com.MateStudy.MateStudy.dto.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Slf4j
@Getter
@Setter
@ToString
public class CustomedMemberDTO extends User {
    private String id;
    private String name;
    private String phone;
    private String email;

    public CustomedMemberDTO(
            String username,
            String pwd,
            String name,
            String phone,
            String email,
            Collection<? extends GrantedAuthority> authorities) {
        /*
         * enabled : true
         * accountNonExpired : true
         * credentialsNonExpired : true
         * accountNonLocekd : true
         * */
        super(username, pwd, true, true, true, true, authorities);
        this.id = username;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}