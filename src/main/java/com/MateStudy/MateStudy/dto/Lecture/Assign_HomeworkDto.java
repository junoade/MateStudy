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
    /**
     * DB에 과제등록시 hwId 자동 생성되도록 수정
     * @see Assign_Homework
     */
    private Long hwId;
    // private Teaching_Lecture t_lec;
    private String instId;
    private String lecCode;
    private Long subCode;
    private String title;
    private String content;
    private LocalDateTime dueDate;
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
    public Assign_HomeworkDto( String instId, String lecCode, Long subCode,
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
    public Assign_HomeworkDto(Long hwId, String instId, String lecCode, Long subCode,
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
