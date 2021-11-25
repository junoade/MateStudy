package com.MateStudy.MateStudy.domain.account;

import com.MateStudy.MateStudy.domain.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/* JPA를 사용해서 application.yml에서 create 또는 create-drop하게 되면 MEMBER라는 DB를 생성*/
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "MEMBER")
public class Member extends BaseEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> role = new HashSet<>();

    /* MEMBER ROLE를 별도로 뺴냄 */
    public void addMemberRole(MemberRole memberRole){
        role.add(memberRole);
    }
}
