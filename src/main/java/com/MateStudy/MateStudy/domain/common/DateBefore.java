package com.MateStudy.MateStudy.domain.common;

import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DateBefore {

    public static String getIntervalDate(LocalDateTime dueDate){
        LocalDate due = dueDate.toLocalDate();
        LocalDate now = LocalDate.now();

        if(ChronoUnit.DAYS.between(now,due) == 0){
            return "오늘";
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

    public static List<String> getIntervalPair(List<Pair<Assign_HomeworkDto, Optional<Lecture>>> list) {
        List<String> result = new ArrayList<>();
        for(Pair<Assign_HomeworkDto, Optional<Lecture>> pair :list){
            String remainDate = getIntervalDate(pair.getFirst().getDueDate());
            result.add(remainDate);
        }
        return result;
    }

}
