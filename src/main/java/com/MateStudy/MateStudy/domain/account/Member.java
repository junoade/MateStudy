package com.MateStudy.MateStudy.domain.account;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/* JPA를 사용해서 application.yml에서 create 또는 create-drop하게 되면 MEMBER라는 DB를 생성*/
@Entity
@Table(name = "MEMBER")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    /*@Id
    @GeneratedValue
    private long seq;*/

    @Id
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate;

    @Builder
    public Member(String id, String pwd){
        this.id = id;
        this.pwd = pwd;
    }
}
