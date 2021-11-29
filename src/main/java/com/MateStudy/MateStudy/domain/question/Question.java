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
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"stId", "instId", "lecCode", "subCode"})
@Table(name = "QUESTION")
public class Question extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "instId", referencedColumnName = "instId",insertable = false, updatable = false),
            @JoinColumn(name = "lecCode", referencedColumnName = "lecCode",insertable = false, updatable = false),
            @JoinColumn(name = "subCode", referencedColumnName = "subCode",insertable = false, updatable = false),
            @JoinColumn(name = "stId", nullable = true, referencedColumnName = "stId", insertable = false, updatable = false)
    })
    private Taking_Lecture t_lec;

    @Column(name="stId", nullable = false)
    private String stId;

    @Column(name="instId", nullable = false)
    private String instId;
    @Column(name="lecCode", nullable = false)
    private String lecCode;
    @Column(name="subCode", nullable = false)
    private Long subCode;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;
}
