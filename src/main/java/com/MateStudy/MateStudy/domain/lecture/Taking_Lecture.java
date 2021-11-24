package com.MateStudy.MateStudy.domain.lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import lombok.*;

import javax.persistence.*;

/**
 * 특정 강좌를 수강하는 학생들을 담기 위한 엔티티 클래스
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"stId","instId", "lecCode", "subCode"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "TAKE_LECTURE")
@IdClass(Taking_LectureId.class)
public class Taking_Lecture {
    /* MEMBER 엔티티와 맵핑( 학생을 가르킴) */
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stId")
    private Member stId;

    /* LECTURE 엔티티와 맵핑 (등록되어있는 실습 강좌의 학수번호 가르킴 */
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "instLecCode")
    @JoinColumns({
            @JoinColumn(name = "instId", referencedColumnName = "instId",insertable = false, updatable = false),
            @JoinColumn(name = "lecCode", referencedColumnName = "lecCode",insertable = false, updatable = false),
            @JoinColumn(name = "subCode", referencedColumnName = "subCode",insertable = false, updatable = false)
    })
    private Teaching_Lecture t_lec;

    /* LECTURE 엔티티와 맵핑 (등록되어있는 실습 강좌의 분반정보 가르킴)*/

    @Column(name="instId")
    private String instId;

    @Column(name="lecCode")
    private String lecCode;

    @Column(name="subCode")
    private long subCode;

}
