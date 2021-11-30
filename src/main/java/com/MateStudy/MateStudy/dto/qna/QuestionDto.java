package com.MateStudy.MateStudy.dto.qna;

import com.MateStudy.MateStudy.domain.question.Question;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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
    private String date;

    public Question toEntity(){
        return Question.builder()
                .qno(qno)
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public QuestionDto(Long qno, String stId, String instId, String lecCode, Long subCode,
                       String title, String content, String remainDate) {
        this.qno = qno;
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.title = title;
        this.content = content;
        this.date = remainDate;
    }
}