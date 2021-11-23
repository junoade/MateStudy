package com.MateStudy.MateStudy.domain.lecture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Spring Data JPA에서 RDBMS의 composite key 표현을 위해 직렬화
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LectureId implements Serializable {
    //private static final serialVersionUID; 사용안하면 class의 기본 해시값 사용함
    @EqualsAndHashCode.Include
    @Id
    protected String lecCode; // 학수번호
    @EqualsAndHashCode.Include
    @Id
    protected long subCode; // 분반번호
}
