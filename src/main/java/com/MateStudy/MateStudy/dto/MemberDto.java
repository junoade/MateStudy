package com.MateStudy.MateStudy.dto;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.account.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class MemberDto {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String phone;
    private UserRole role;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .pwd(pwd)
                .build();
    }

    @Builder
    public MemberDto(String id, String pwd){
        this.id = id;
        this.pwd = pwd;
    }
}
