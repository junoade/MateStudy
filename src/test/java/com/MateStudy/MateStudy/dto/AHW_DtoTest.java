package com.MateStudy.MateStudy.dto;

import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
public class AHW_DtoTest {
    //private Long hwId;
    private String instId;
    private String lecCode;
    private Long subCode;
    private String title;
    private String content;
    private LocalDateTime dueDate; // <-
    private Boolean isDone;

    public Assign_Homework toEntity(Teaching_Lecture t_lec) {
        return Assign_Homework.builder()
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .title(title)
                .content(content)
                .dueDate(dueDate)
                .isDone(false)
                .build();
    }

    public AHW_DtoTest(String instId, String lecCode, Long subCode,
                       String title, String content, LocalDateTime dueDate, Boolean isDone) {
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.isDone = false;
    }
}
