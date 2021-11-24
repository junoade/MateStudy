package com.MateStudy.MateStudy.dto;

import com.MateStudy.MateStudy.domain.homework.Homework;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
public class HomeworkDto {
    private Long hno;
    private String content;
    private String title;
    private String dueDate;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public Homework toEntity(){
        return Homework.builder()
                .hno(hno)
                .content(content)
                .title(title)
                .dueDate(dueDate)
                .build();
    }

    @Builder
    public HomeworkDto(Long hno, String content, String title,
                       String dueDate, LocalDateTime regDate, LocalDateTime modDate){
        this.hno = hno;
        this.content = content;
        this.title = title;
        this.dueDate = dueDate;
        this.regDate = regDate;
        this.modDate = modDate;
    }

}
