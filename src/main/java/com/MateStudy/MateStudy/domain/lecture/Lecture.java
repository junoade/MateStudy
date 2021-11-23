package com.MateStudy.MateStudy.domain.lecture;

import com.MateStudy.MateStudy.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "LECTURE")
@IdClass(LectureId.class)
public class Lecture extends BaseEntity {
    /*Composit key를 이용하여 직렬화 필요*/

    @Id
    @Column(name="lecCode")
    private String lecCode; // 학수번호

    @Id
    @Column(name="subCode")
    private long subCode; // 분반번호

    @Column(nullable = false)
    private String lecTitle;

}
