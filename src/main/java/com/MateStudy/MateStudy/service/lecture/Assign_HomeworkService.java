package com.MateStudy.MateStudy.service.lecture;

import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.repository.lecture.TeachLectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 특정 강좌의 교수자가 해당 강좌에 과제들을 부여하게 하는 Service
 * @see com.MateStudy.MateStudy.domain.homework.Assign_Homework
 * @see Assign_HomeworkDto
 * @see Assign_HomeworkRepository
 */
@Slf4j
@Service
public class Assign_HomeworkService {
    @Autowired
    private Assign_HomeworkRepository assign_homeworkRepository;

    /**
     * hwId와 함께 저장 하는 경우
     * 따로 Assign_HomeworkDto의 교수자, 학수번호, 분반 검증 코드는 작성하지 않음
     * try-catch문으로 save 안될 때 log 출력
     * @param assign_homeworkDto
     */
    @Transactional
    public void saveHomework(Assign_HomeworkDto assign_homeworkDto){
        log.info("saving...");
        try {
            assign_homeworkRepository.save(assign_homeworkDto.toEntityWithId());
        }catch(Exception e){
            log.info("invalid user data");
            e.printStackTrace();
        }
    }
    /**
     * hwId와 함께 저장하지 않는 경우
     * 따로 Assign_HomeworkDto의 교수자, 학수번호, 분반 검증 코드는 작성하지 않음
     * try-catch문으로 save 안될 때 log 출력
     * @param assign_homeworkDto
     */
    @Transactional
    public void saveHomeworkAuto(Assign_HomeworkDto assign_homeworkDto){
        log.info("saving...");
        try {
            assign_homeworkRepository.save(assign_homeworkDto.toEntityAuto());
        }catch(Exception e){
            log.info("invalid user data");
            e.printStackTrace();
        }
    }
}
