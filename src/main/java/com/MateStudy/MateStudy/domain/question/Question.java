package com.MateStudy.MateStudy.domain.question;

import com.MateStudy.MateStudy.domain.common.BaseEntity;
import com.MateStudy.MateStudy.domain.lecture.Taking_Lecture;
import lombok.*;

import javax.persistence.*;

/**
 * 등록된 실습에서 한 학생이 갖는 여러 질문들을 처리
 * MEMBER_LECTURE : QUESTION  Maximum cardinality 1:N
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"instId", "lecCode", "subCode", "stId"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "QUESTION")
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // hwId를 추가하면 따로 Id 등록안해서 자동으로 생성하게끔
    private Long qno;

    /* TAKE_LECTURE 엔티티와 맵핑 - 강의를 듣는 학생이 가지는 제출한 과제들 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "instId", referencedColumnName = "instId",insertable = false, updatable = false),
            @JoinColumn(name = "lecCode", referencedColumnName = "lecCode",insertable = false, updatable = false),
            @JoinColumn(name = "subCode", referencedColumnName = "subCode",insertable = false, updatable = false),
            @JoinColumn(name = "stId", nullable = true, referencedColumnName = "stId", insertable = false, updatable = false)
    })
    private Taking_Lecture t_lec;

    @Column(name="instId", nullable = false)
    private String instId;
    @Column(name="lecCode", nullable = false)
    private String lecCode;
    @Column(name="subCode", nullable = false)
    private Long subCode;
    @Column(name="stId", nullable = false)
    private String stId;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

}
