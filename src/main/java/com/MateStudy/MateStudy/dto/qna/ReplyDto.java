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
    private Question question;
    private Long qno;
    private String title;
    private String content;
    private String date;
    private String stName;
    private String instName;
    private String lecTitle;

    public Reply toEntity(){
        return Reply.builder()
                .rno(rno)
                .qno(question)
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public ReplyDto(Long rno, Long qno, String title, String content, String date, String stName, String instName, String lecTitle){
        this.rno = rno;
        this.qno = qno;
        this.title = title;
        this.content = content;
        this.date = date;
        this.stName = stName;
        this.instName = instName;
        this.lecTitle = lecTitle;
    }
}