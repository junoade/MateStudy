package com.MateStudy.MateStudy.domain.homework;

import com.MateStudy.MateStudy.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *  등록된 실습이 갖는 여러 과제들의  관계
 *  MEMBER_LECTURE : QUESTION
 *  Maximum cardinality 1:N
 *  Minimum cardinality M:O
 */

@Entity
@Data
@NoArgsConstructor
public class Homework extends BaseEntity {
    @Id
    private Long hno;

    @Column
    private String content;

    @Column
    private String title;

    @Column
    private String dueDate;

    @Builder
    public Homework(Long hno, String content, String title, String dueDate){
        this.hno = hno;
        this.content = content;
        this.title = title;
        this.dueDate = dueDate;
    }
}
