package com.MateStudy.MateStudy.domain.lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class TeachingLecId implements Serializable {
    //private static final serialVersionUID; 사용안하면 class의 기본 해시값 사용함\

    protected String instId;

    protected String lecCode;

    protected long subCode;

}
