package com.MateStudy.MateStudy.service.lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.Lecture.TeachLectureDto;
import com.MateStudy.MateStudy.repository.MemberRepository;
import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.repository.lecture.LectureRepository;
import com.MateStudy.MateStudy.repository.lecture.TeachLectureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 강좌 - 교수자 Repository를 활용하여 서비스 제공
 */
@Service
@Slf4j
@AllArgsConstructor
public class TeachLectureService {
    @Autowired
    Assign_HomeworkRepository assign_homeworkRepository;

    @Autowired
    TeachLectureRepository teachingRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    //교수자의 담당 과목을 모두 반환
    public List<LectureDto> getTeachLectureList(String id){
        Optional<Member> member = memberRepository.findById(id);
        List<Teaching_Lecture> list = teachingRepository.findByInstId(member.get());
        List<TeachLectureDto> dtoList = new ArrayList<>();

        for(Teaching_Lecture l : list){
            TeachLectureDto tLDto = TeachLectureDto.builder()
                    .instId(l.getInstId())
                    .lecCode(l.getLecCode())
                    .subCode(l.getSubCode())
                    .build();
            dtoList.add(tLDto);
        }

        List<LectureDto> lecList = new ArrayList<>();

        for(TeachLectureDto tLDto : dtoList){
            Optional<Lecture> lecture = lectureRepository.getOneLecture(tLDto.getLecCode(), tLDto.getSubCode());
            LectureDto lectureDto = LectureDto.builder()
                    .lecCode(lecture.get().getLecCode())
                    .lecTitle(lecture.get().getLecTitle())
                    .subCode(lecture.get().getSubCode())
                    .build();
            lecList.add(lectureDto);
        }
        return lecList;
    }

    //교수자가 등록한 전체 과제리스트 반환
    @Transactional
    public List<Assign_HomeworkDto> getAllHomework(String id){
        List<LectureDto> lectureDtoList = getTeachLectureList(id);
        List<Assign_HomeworkDto> ahdList = new ArrayList<>();
        for(LectureDto lDto : lectureDtoList){
            List<Assign_Homework> ahList;
            ahList = assign_homeworkRepository
                    .getAssignedHomeworks(id, lDto.getLecCode(),lDto.getSubCode());
            for(Assign_Homework ah : ahList){
                Assign_HomeworkDto ahDto = Assign_HomeworkDto.builder()
                        .instId(ah.getInstId())
                        .lecCode(ah.getLecCode())
                        .subCode(ah.getSubCode())
                        .title(ah.getTitle())
                        .content(ah.getContent())
                        .dueDate(ah.getDueDate())
                        .isDone(ah.getIsDone())
                        .build();
                ahdList.add(ahDto);
            }
        }
        return ahdList;
    }
    //교수자의 특정 과목에 대한 과제리스트 반환
    @Transactional
    public List<Assign_HomeworkDto> getHomework(String id, String lecCode, Long subCode){
        Optional<Lecture> lecture = lectureRepository.getOneLecture(lecCode, subCode);
        List<Assign_HomeworkDto> ahdList = new ArrayList<>();
        List<Assign_Homework> ahList;
        ahList = assign_homeworkRepository.getAssignedHomeworks(id,lecCode,subCode);
        for(Assign_Homework ah : ahList){
            Assign_HomeworkDto ahDto = Assign_HomeworkDto.builder()
                    .instId(ah.getInstId())
                    .lecCode(ah.getLecCode())
                    .subCode(ah.getSubCode())
                    .title(ah.getTitle())
                    .content(ah.getContent())
                    .dueDate(ah.getDueDate())
                    .isDone(ah.getIsDone())
                    .build();
            ahdList.add(ahDto);
        }
        return ahdList;
    }

    /* 교수자의 학번, 등록하고자 하는 학수번호, 분반 정보 입력시 "학습 중 강좌"에 교수자 등록*/
    @Transactional
    public void setInstructor(String id, String lecCode, long subCode){
        Optional<Lecture> lecture = lectureRepository.getOneLecture(lecCode, subCode);
        Optional<Member> instructor = memberRepository.findByName(id);

        /* 존재하는 강좌, 교수자인지 검증 후 삽입*/
        if(lecture.isPresent() && instructor.isPresent()){
            Teaching_Lecture result = Teaching_Lecture.builder()
                    .instId(instructor.get())
                    .lecCode(lecture.get().getLecCode())
                    .subCode(lecture.get().getSubCode())
                    .build();
            teachingRepository.save(result);
        }else{
            log.info("강좌에 교수자 지정 실패");
        }
    }

}
