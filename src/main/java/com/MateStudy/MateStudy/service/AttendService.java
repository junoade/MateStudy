package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.attendance.Attend;
import com.MateStudy.MateStudy.dto.AttendDto;
import com.MateStudy.MateStudy.repository.attend.AttendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AttendService {

    @Autowired
    AttendRepository attendRepository;

    @Transactional
    public Long setAttendStatus(String stId, String instId, String lecCode, Long subCode, Long week, String status) {
        Attend attend = Attend.builder()
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                //.week(week)
                .status(status)
                .build();

        return attendRepository.save(attend).getAttendId();
    }

    /*@Transactional
    public List<AttendDto> getMyClassAttendance(String lecCode, Long subCode, Long week){

    }
*/
    @Transactional
    public List<AttendDto> getMyStudentsAttend(String lecCode, Long subCode) {
        List<Attend> attendList = attendRepository.getEveryAttend(lecCode, subCode);
        List<AttendDto> attendDtos = new ArrayList<>();
        for (Attend attend : attendList) {
            AttendDto dto = AttendDto.builder()
                    .attendId(attend.getAttendId())
                    //.week(attend.getWeek())
                    .status(attend.getStatus())
                    .stId(attend.getStId())
                    .instId(attend.getInstId())
                    .lecCode(attend.getLecCode())
                    .subCode(attend.getSubCode())
                    .build();
            attendDtos.add(dto);
        }
        return attendDtos;
    }

    /*@Transactional
    public List<AttendDto> getMyAttendLog(int size, String stId, String instId, String lecCode, Long subCode) {
        List<Attend> list = attendRepository.getMyAttendRecord(stId, instId, lecCode, subCode);
        List<AttendDto> attendDtos = new ArrayList<>();
        Long temp_week = 0L;
        //2트
        for (int i = 0; i < size; i++) {
            if (list.get(i) == null) {
                AttendDto temp = new AttendDto("미정", temp_week, stId, instId, lecCode, subCode);
                attendDtos.add(temp);
                attendRepository.save(temp.toEntityAuto());
                temp_week += 1L;
            } else {
                AttendDto temp = AttendDto.builder()
                        .status(list.get(i).getStatus())
                        .week(list.get(i).getWeek())
                        .stId(list.get(i).getStId())
                        .instId(list.get(i).getInstId())
                        .lecCode(list.get(i).getLecCode())
                        .subCode(list.get(i).getSubCode())
                        .build();
                attendDtos.add(temp);
            }
        }
        // 1트 
        for(Attend l : list){
            if(l == null){
                AttendDto temp = new AttendDto("미정", temp_week, stId, instId, lecCode, subCode);
                attendDtos.add(temp);
                temp_week += 1L;
            }else{
                AttendDto temp = AttendDto.builder()
                        .status(l.getStatus())
                        .week(l.getWeek())
                        .stId(l.getStId())
                        .instId(l.getInstId())
                        .lecCode(l.getLecCode())
                        .subCode(l.getSubCode())
                        .build();
                attendDtos.add(temp);
            }
        }
        return attendDtos;
    }*/
}
