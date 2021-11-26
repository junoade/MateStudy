package com.MateStudy.MateStudy.dto.Lecture;

import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.homework.Submit_Homework;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
public class Submit_HomeworkDto {
    /**
     * DB에 과제등록시 submitId 자동 생성
     *
     * @see com.MateStudy.MateStudy.domain.homework.Submit_Homework
     */

    private Long submitId;
    private String stId;
    private String instId;
    private String lecCode;
    private Long subCode;
    private Assign_Homework hwId;
    private String title;
    private String content;
    private Integer grade;

    /* submitId 자동 생성 */
    public Submit_Homework toEntityAuto() {
        return Submit_Homework.builder()
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .hwId(hwId)
                .title(title)
                .content(content)
                .grade(grade)
                .build();
    }

    @Builder
    public Submit_HomeworkDto(String stId, String instId, String lecCode, Long subCode, Assign_Homework hwId,
                              String title, String content, Integer grade) {
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.hwId = hwId;
        this.title = title;
        this.content = content;
        this.grade = grade;
    }

    /* submitId 명시적으로 지정시 */
    public Submit_Homework toEntityWithId() {
        return Submit_Homework.builder()
                .submitId(submitId)
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .hwId(hwId)
                .title(title)
                .content(content)
                .grade(grade)
                .build();
    }

    /* TODO Builder는 하나만 지정해야하는 것 같은데?? */
    @Builder
    public Submit_HomeworkDto(Long submitId, String stId, String instId, String lecCode, Long subCode, Assign_Homework hwId,
                              String title, String content, Integer grade) {
        this.submitId = submitId;
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.hwId = hwId;
        this.title = title;
        this.content = content;
        this.grade = grade;
    }

}
