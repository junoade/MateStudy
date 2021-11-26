package com.MateStudy.MateStudy.domain.common;

import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class DateComparator implements Comparator<Assign_HomeworkDto> {
    @Override
    public int compare(Assign_HomeworkDto first, Assign_HomeworkDto second){
        LocalDateTime firstValue = first.getDueDate();
        LocalDateTime secondValue = second.getDueDate();

        if(ChronoUnit.DAYS.between(firstValue,secondValue) > 0){
            return -1;
        }else if(ChronoUnit.DAYS.between(firstValue,secondValue) < 0){
            return 1;
        }else return 0;
    }
}
