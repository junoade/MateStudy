package com.MateStudy.MateStudy.domain.common;

import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class DateBefore {

    public static String getIntervalDate(LocalDateTime dueDate){
        LocalDate due = dueDate.toLocalDate();
        LocalDate now = LocalDate.now();

        if(ChronoUnit.DAYS.between(now,due) == 0){
            return "D-Day";
        }
        return ChronoUnit.DAYS.between(now,due) + "일 전";
    }

    public static List<String> getInterval(List<Assign_HomeworkDto> list) {
        List<String> result = new ArrayList<>();
        for(Assign_HomeworkDto l :list){
            String remainDate = getIntervalDate(l.getDueDate());
            result.add(remainDate);
        }
        return result;
    }

}
