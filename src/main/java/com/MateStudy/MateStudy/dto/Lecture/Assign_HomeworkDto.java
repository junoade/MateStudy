package com.MateStudy.MateStudy.dto.Lecture;

import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
public class Assign_HomeworkDto {
    private Long hwId;
    private Teaching_Lecture t_lec;
    private String instId;
    private String lecCode;
    private Long subCode;
    private String title;
    private String content;
    private String dueDate;
    private Boolean isDone;

    public Assign_Homework toEntity(Teaching_Lecture t_lec){
        return Assign_Homework.builder()
                .hwId(hwId)
                .t_lec(t_lec)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .title(title)
                .content(content)
                .dueDate(dueDate)
                .isDone(false)
                .build();
    }

    public Assign_HomeworkDto(Long hwId, String instId, String lecCode, Long subCode,
                              String title, String content, String dueDate){
        this.hwId = hwId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.isDone = false;
    }
}
