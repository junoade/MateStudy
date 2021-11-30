package com.MateStudy.MateStudy.dto.qna;

import com.MateStudy.MateStudy.domain.question.Question;
import com.MateStudy.MateStudy.domain.question.Reply;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class ReplyDto {
    private Long rno;
    private Question qno;
    private String title;
    private String content;
    private String date;

    public Reply toEntity(){
        return Reply.builder()
                .rno(rno)
                .qno(qno)
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public ReplyDto(Long rno, Question qno, String title, String content, String date){
        this.rno = rno;
        this.qno = qno;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}