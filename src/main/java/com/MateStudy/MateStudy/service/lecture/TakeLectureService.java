package com.MateStudy.MateStudy.service.lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.homework.Submit_Homework;
import com.MateStudy.MateStudy.domain.lecture.Taking_Lecture;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Submit_HomeworkDto;
import com.MateStudy.MateStudy.dto.Lecture.TakeLectureDto;
import com.MateStudy.MateStudy.repository.MemberRepository;
import com.MateStudy.MateStudy.repository.lecture.TakeLectureRepository;
import com.MateStudy.MateStudy.repository.lecture.TeachLectureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 학생 - 현재 교수자가 배정된 강좌에 관한 TakeLecutreRepository를 활용하여 서비스 제공
 */
@Service
@Slf4j
@AllArgsConstructor
public class TakeLectureService {
    @Autowired
    TakeLectureRepository takeLectureRepository;

    @Autowired
    TeachLectureRepository teachLectureRepository;

    @Autowired
    MemberRepository memberRepository;

    /* 학생 - 교수자의 강좌에 지정하기 */
    @Transactional
    public void setStudent(String stId, String instId, String lecCode, Long subCode){
        Optional<Teaching_Lecture> availLecture = teachLectureRepository.getCurrentLecture(instId, lecCode, subCode);
        Optional<Member> student = memberRepository.findById(stId);

        /* 배정가능한 강좌, 학생인지 검증 후 삽입 */
        if(availLecture.isPresent() && student.isPresent()){
            Taking_Lecture result = Taking_Lecture.builder()
                    .stId(student.get())
                    .instId(availLecture.get().getInstId().getId())
                    .lecCode((availLecture.get().getLecCode()))
                    .subCode(availLecture.get().getSubCode())
                    .build();
            takeLectureRepository.save(result);
        }else{
            log.info("강좌에 학생 지정 실패");
        }
    }

    /* 학생이 수강하는 과목을 모두 반환 */
    public List<TakeLectureDto> getTakeLectureList(String stId){
        Optional<Member> member = memberRepository.findById(stId);
        List<Taking_Lecture> list = takeLectureRepository.findByStId(member.get());
        List<TakeLectureDto> dtoList = new ArrayList<>();

        for(Taking_Lecture l : list){
            TakeLectureDto dto = TakeLectureDto.builder()
                    .stId(l.getStId())
                    .instId(l.getInstId())
                    .lecCode(l.getLecCode())
                    .subCode(l.getSubCode())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }

    /* 학생이 제출한 모든 과제 리스트 반환 */
   /* @Transactional
    public List<Submit_HomeworkDto> getAllSubmits(String stId){
        List<TakeLectureDto> takingLecutreList = new ArrayList<>();
        *//*List<Submit_HomeworkDto> shwList = new ArrayList<>();
        for(TakeLectureDto t : takingLecutreList){

        }*//*
        Optional<Submit_Homework> hws =

    }*/
    /* 학생이 제출한 특정 과목에 대한 과제 리스트 반환 */


}
