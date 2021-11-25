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
    private Long hwId;
    private String instId;
    private String lecCode;
    private Long subCode;
    private String title;
    private String content;
    private LocalDateTime dueDate; // <-
    private Boolean isDone;

    /* hwId 자동 생성할 떄 */
    public Assign_Homework toEntityAuto(){
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
    public AHW_DtoTest( String instId, String lecCode, Long subCode,
                               String title, String content, LocalDateTime dueDate, Boolean isDone){
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    /* hwId 명시적으로 지정할 때 */
    public Assign_Homework toEntityWithId(){
        return Assign_Homework.builder()
                .hwId(hwId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .title(title)
                .content(content)
                .dueDate(dueDate)
                .isDone(false)
                .build();
    }
    public AHW_DtoTest(Long hwId, String instId, String lecCode, Long subCode,
                              String title, String content, LocalDateTime dueDate, Boolean isDone){
        this.hwId = hwId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }
}
