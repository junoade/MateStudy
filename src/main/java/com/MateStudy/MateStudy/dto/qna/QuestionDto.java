package com.MateStudy.MateStudy.dto.qna;

import com.MateStudy.MateStudy.domain.attendance.Attend;
import com.MateStudy.MateStudy.domain.question.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class QuestionDto {
    private Long qno;
    private String stId;
    private String instId;
    private String lecCode;
    private Long subCode;
    private String title;
    private String content;

    public Question toEntityAuto(){
        return Question.builder()
                //.qno(qno) 자동 생성
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .title(title)
                .content(content)
                .build();
    }

    public QuestionDto(String stId, String instId, String lecCode, Long subCode, String title, String content){
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.title = title;
        this.content = content;
    }

    /* submitId 명시적으로 지정시 */
    public Question toEntityWithId() {
        return Question.builder()
                .qno(qno) //자동 생성
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .title(title)
                .content(content)
                .build();
    }

    public QuestionDto(Long qno, String stId, String instId, String lecCode, Long subCode, String title, String content){
        this.qno = qno;
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.title = title;
        this.content = content;
    }
}
