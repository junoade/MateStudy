package com.MateStudy.MateStudy.domain.account;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String id;
    private String password;
}
