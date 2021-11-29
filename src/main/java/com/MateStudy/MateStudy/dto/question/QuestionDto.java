package com.MateStudy.MateStudy.dto.question;

import com.MateStudy.MateStudy.domain.question.Question;
import lombok.Builder;
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

    public Question toEntity(){
        return Question.builder()
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
                              String title, String content) {
        this.qno = qno;
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
        this.title = title;
        this.content = content;
    }
}
