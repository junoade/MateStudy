package com.MateStudy.MateStudy.domain.homework;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class Shw_FileId implements Serializable {
    protected Long Fid;
    protected Long submitId;
}
