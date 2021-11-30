package com.MateStudy.MateStudy.domain.attendance;

import com.MateStudy.MateStudy.domain.common.BaseEntity;
import com.MateStudy.MateStudy.domain.lecture.Taking_Lecture;
import lombok.*;

import javax.persistence.*;

/**
 * 실습에 참여한 학생들을 관리하는 엔티티
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"instId", "lecCode", "subCode", "stId"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "ATTENDANCE")
public class Attend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // hwId를 추가하면 따로 Id 등록안해서 자동으로 생성하게끔
    private Long attendId;

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

    /* 해당 과목에서 주차 정보등을 담는다 */
    @Column(name="week")
    private Long week;

    /* 출석 여부 yet, attend, late, absence, vacancy */
    @Column(name="status", columnDefinition = "varchar(255) DEFAULT 'YET' CHECK (status IN ('YET', 'ATTEND', 'LATE', 'ABSENCE', 'VACANCY'))")
    private String status;

}
