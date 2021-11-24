package com.MateStudy.MateStudy.dto.Lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class TeachLectureDto {
    private Member instId;
    private String lecCode;
    private Long subCode;

    public Teaching_Lecture toEntity(){
        return Teaching_Lecture.builder()
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .build();
    }

    @Builder
    public TeachLectureDto(Member instId, String lecCode, Long subCode){
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
    }
}
