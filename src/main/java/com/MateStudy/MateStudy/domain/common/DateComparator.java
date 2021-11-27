package com.MateStudy.MateStudy.domain.common;

import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Optional;

public class DateComparator implements Comparator<Pair<Assign_HomeworkDto, Optional<Lecture>>> {
//    @Override
//    public int compare(Assign_HomeworkDto first, Assign_HomeworkDto second){
//        LocalDateTime firstValue = first.getDueDate();
//        LocalDateTime secondValue = second.getDueDate();
//
//        if(ChronoUnit.DAYS.between(firstValue,secondValue) > 0){
//            return -1;
//        }else if(ChronoUnit.DAYS.between(firstValue,secondValue) < 0){
//            return 1;
//        }else return 0;
//    }

    @Override
    public int compare(Pair<Assign_HomeworkDto, Optional<Lecture>> first, Pair<Assign_HomeworkDto, Optional<Lecture>> second){
        LocalDateTime firstValue = first.getFirst().getDueDate();
        LocalDateTime secondValue = second.getFirst().getDueDate();

        if(ChronoUnit.DAYS.between(firstValue,secondValue) > 0){
            return -1;
        }else if(ChronoUnit.DAYS.between(firstValue,secondValue) < 0){
            return 1;
        }else return 0;
    }
}
