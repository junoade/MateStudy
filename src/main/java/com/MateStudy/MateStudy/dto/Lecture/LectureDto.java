package com.MateStudy.MateStudy.dto.Lecture;

import com.MateStudy.MateStudy.domain.lecture.Lecture;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class LectureDto {
    private String lecCode;
    private Long subCode;
    private String lecTitle;

    public Lecture toEntity(){
        return Lecture.builder()
                .lecCode(lecCode)
                .subCode(subCode)
                .lecTitle(lecTitle)
                .build();
    }

    @Builder
    public LectureDto(String lecCode, Long subCode, String lecTitle){
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.lecTitle = lecTitle;
    }
}
