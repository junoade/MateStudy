package com.MateStudy.MateStudy.domain.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* 회원의 역할 구분을 위한 enum 클래스*/
@AllArgsConstructor
@Getter
public enum MemberRole {
    VISITOR("ROLE_VISITOR"),
    STUDENT("ROLE_STUDENT"),
    INSTRUCTOR("ROLE_INSTRUCTOR"),
    ADMIN("ROLE_ADMIN");
    private String value;
}
