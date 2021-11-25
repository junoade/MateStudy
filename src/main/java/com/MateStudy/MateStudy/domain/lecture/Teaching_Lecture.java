package com.MateStudy.MateStudy.domain.lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * 관리자가 생성 및 교원 임명
 * 또는 교수가 생성한 강좌에 대해
 * Member_lecture에 멤버(학생, 교원) - 실습강의의 mapping 관계 형성
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"instId", "lecCode", "subCode"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "TEACH_LECTURE")
@IdClass(TeachingLecId.class)
public class Teaching_Lecture extends BaseEntity {
    /* MEMBER 엔티티와 맵핑( 교수자를 가르킴) */
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instId")
    private Member instId;

    /* LECTURE 엔티티와 맵핑 (등록되어있는 실습 강좌의 학수번호 가르킴 */
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "instLecCode")
    @JoinColumns({
            @JoinColumn(name = "lecCode", referencedColumnName = "lecCode",insertable = false, updatable = false),
            @JoinColumn(name = "subCode", referencedColumnName = "subCode",insertable = false, updatable = false)
    })
    private Lecture lec;

    /* LECTURE 엔티티와 맵핑 (등록되어있는 실습 강좌의 분반정보 가르킴)*/
    @Column(name="lecCode")
    private String lecCode;

    @Column(name="subCode")
    private long subCode;

}
