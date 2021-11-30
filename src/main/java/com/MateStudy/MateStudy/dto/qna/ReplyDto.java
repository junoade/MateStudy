package com.MateStudy.MateStudy.dto.qna;

import com.MateStudy.MateStudy.domain.question.Question;
import com.MateStudy.MateStudy.domain.question.Reply;
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

    public Reply toEntityAuto(){
        return Reply.builder()
                //.rno(rno) 자동 생성
                .qno(qno)
                .title(title)
                .content(content)
                .build();
    }

    public ReplyDto(Question qno, String title, String content){
        this.qno=qno;
        this.title = title;
        this.content = content;
    }

    /* submitId 명시적으로 지정시 */
    public Reply toEntityWithId() {
        return Reply.builder()
                .rno(rno)
                .qno(qno)
                .title(title)
                .content(content)
                .build();
    }

    public ReplyDto(Long rno, Question qno, String title, String content){
        this.rno=rno;
        this.qno=qno;
        this.title = title;
        this.content = content;
    }
}
