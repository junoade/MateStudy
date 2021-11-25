package com.MateStudy.MateStudy.domain.homework;


import com.MateStudy.MateStudy.domain.common.BaseEntity;
import com.MateStudy.MateStudy.domain.lecture.Taking_Lecture;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *  강좌에 속한 교수자가 부여하는 과제들의 테이블
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
@ToString(exclude = {"instId", "lecCode", "subCode"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "ASSIGN_HW")
public class Assign_Homework extends BaseEntity {

    @Id
    private Long hwId;

    /* TEACH_LECTURE 엔티티와 맵핑 (등록되어있는 실습 강좌에서 등록한 과제 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "instId", referencedColumnName = "instId",insertable = false, updatable = false),
            @JoinColumn(name = "lecCode", referencedColumnName = "lecCode",insertable = false, updatable = false),
            @JoinColumn(name = "subCode", referencedColumnName = "subCode",insertable = false, updatable = false)
    })
    private Teaching_Lecture t_lec;

    /* TEACH_LECTURE 엔티티와 맵핑 (등록되어있는 실습 강좌의 교수자, 학수번호, 분반정보 가르킴)*/
    @Column(name="instId")
    private String instId;
    @Column(name="lecCode")
    private String lecCode;
    @Column(name="subCode")
    private Long subCode;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="dueDate")
    private LocalDateTime dueDate; // 수정함

    @Column(name="isDone")
    private Boolean isDone;

}
