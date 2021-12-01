package com.MateStudy.MateStudy.domain.common;

import com.MateStudy.MateStudy.dto.qna.QuestionDto;
import com.MateStudy.MateStudy.dto.qna.ReplyDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class QnoCompare implements Comparator<QuestionDto>{
    @Override
    public int compare(QuestionDto first, QuestionDto second){
        Long firstValue = first.getQno();
        Long secondValue = second.getQno();

        if(firstValue > secondValue){
            return -1;
        }else if(firstValue < secondValue){
            return 1;
        }else return 0;
    }
}
