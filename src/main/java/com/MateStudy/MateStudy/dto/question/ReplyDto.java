package com.MateStudy.MateStudy.dto.question;

import com.MateStudy.MateStudy.domain.question.Question;
import com.MateStudy.MateStudy.domain.question.Reply;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class ReplyDto {
    private Long Ano;
    private Question qno;
    private String title;
    private String content;

    public Reply toEntity(){
        return Reply.builder()
                .Ano(Ano)
                .qno(qno)
                .title(title)
                .content(content)
                .build();
    }

    public ReplyDto(Long Ano, Question qno, String title, String content){
        this.Ano = Ano;
        this.qno = qno;
        this.title = title;
        this.content = content;
    }
}
