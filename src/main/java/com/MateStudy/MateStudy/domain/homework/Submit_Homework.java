package com.MateStudy.MateStudy.domain.homework;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.common.BaseEntity;
import com.MateStudy.MateStudy.domain.lecture.Taking_Lecture;
import com.MateStudy.MateStudy.domain.lecture.TeachingLecId;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *  등록된 실습이 갖는 여러 과제들의  관계
 *  TEACH_LECTURE : HOMEWORK
 *  Maximum cardinality 1:N
 *  Minimum cardinality M:O
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"hwId","stId","instId", "lecCode", "subCode"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "SUBMIT_HW")
public class Submit_Homework extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // hwId를 추가하면 따로 Id 등록안해도 자동으로 생성하게끔
    private Long submitId; // Wrapper 클래스인 Long 쓰는 이유, long과 같은 primitive 타입의 기본값은 0인데, 데이터베이스에서 데이터가 없다는 의미인지, id가 0인지 혼동

    /* ASSIGN_HOMEWORK 엔티티와 맵핑 - 강의에서 부여한 과제들 */
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stId", nullable = true)
    private Taking_Lecture stId;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hwId", nullable = false)
    private Assign_Homework hwId;

    /* TAKE_LECTURE 엔티티와 맵핑 - 강의를 듣는 학생이 가지는 제출한 과제들 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "instId", referencedColumnName = "instId",insertable = false, updatable = false),
            @JoinColumn(name = "lecCode", referencedColumnName = "lecCode",insertable = false, updatable = false),
            @JoinColumn(name = "subCode", referencedColumnName = "subCode",insertable = false, updatable = false),
            @JoinColumn(name = "stId", nullable = true, referencedColumnName = "stId", insertable = false, updatable = false)
    })
    private Taking_Lecture t_lec;

    /* LECTURE 엔티티와 맵핑 (등록되어있는 실습 강좌의 분반정보 가르킴)*/
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

    @Column(name="grade")
    private Integer grade;

}
