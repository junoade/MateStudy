package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.attendance.Attend;
import com.MateStudy.MateStudy.dto.AttendDto;
import com.MateStudy.MateStudy.repository.attend.AttendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class AttendService {

    @Autowired
    AttendRepository attendRepository;

    @Transactional
    public Long setAttendStatus(String stId, String instId, String lecCode, Long subCode, Long week){
        Attend attend = Attend.builder()
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .week(week)
                .build();

        return attendRepository.save(attend).getAttendId();
    }
}
