package com.MateStudy.MateStudy.domain.lecture;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class Taking_LectureId implements Serializable {
    //private static final serialVersionUID; 사용안하면 class의 기본 해시값 사용함\

    protected String stId;

    protected String instId;

    protected String lecCode;

    protected long subCode;
}
