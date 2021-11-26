package com.MateStudy.MateStudy.dto.Lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.lecture.Taking_Lecture;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 강좌에 등록된 교수자의 수업을 듣는 학생들 작업
 */
@ToString
@Data
@NoArgsConstructor
public class TakeLectureDto {
    private Member stId;
    private String instId;  // Taking_Lecture.java에서 String타입으로 참조중
    private String lecCode;
    private Long subCode;

    public Taking_Lecture toEntity(){
        return Taking_Lecture.builder()
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .build();
    }

    @Builder
    public TakeLectureDto(Member stId, String instId, String lecCode, Long subCode){
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
    }

}
