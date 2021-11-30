package com.MateStudy.MateStudy.domain.common;

import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.service.qna.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DateBefore{

    public static String getIntervalDate(LocalDateTime dueDate){
        LocalDateTime due = dueDate;
        LocalDateTime now = LocalDateTime.now();

        if(ChronoUnit.DAYS.between(now,due) == 0){
            if(ChronoUnit.HOURS.between(now,due) == 0){
                if(ChronoUnit.MINUTES.between(now,due) == 0){
                    return "1분 미만";
                }
                return ChronoUnit.MINUTES.between(now,due) + "분 전";
            }
            return ChronoUnit.HOURS.between(now,due) + "시간 전";
        }
        return ChronoUnit.DAYS.between(now,due) + "일 전";
    }

    public static String getIntervalDateAfter(LocalDateTime date){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = date;

        if(ChronoUnit.DAYS.between(from, now) == 0){
            if(ChronoUnit.HOURS.between(from, now) == 0){
                if(ChronoUnit.MINUTES.between(from, now) == 0){
                    return "방금 전";
                }
                return ChronoUnit.MINUTES.between(from, now) + "분 전";
            }
            return ChronoUnit.HOURS.between(from, now) + "시간 전";
        }
        return ChronoUnit.DAYS.between(from, now) + "일 전";
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
