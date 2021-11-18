package com.MateStudy.MateStudy.domain.account;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private Long id;
    private String password;
}
