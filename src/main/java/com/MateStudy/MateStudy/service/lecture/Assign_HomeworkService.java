package com.MateStudy.MateStudy.service.lecture;

import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.repository.lecture.TeachLectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class Assign_HomeworkService {
    @Autowired
    private Assign_HomeworkRepository assign_homeworkRepository;

    @Transactional
    public void saveHomework(Assign_HomeworkDto assign_homeworkDto){
        log.info("saving...");
        Teaching_Lecture teaching_lecture = new Teaching_Lecture();
        assign_homeworkRepository.save(assign_homeworkDto.toEntity(teaching_lecture));
    }
}
