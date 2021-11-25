package com.MateStudy.MateStudy.service;


import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.dto.AHW_DtoTest;
import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.repository.lecture.TeachLectureRepository;
import com.MateStudy.MateStudy.service.lecture.Assign_HomeworkService;
import com.MateStudy.MateStudy.service.lecture.TeachLectureService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
public class Assign_hw_serviceTest {

    @Autowired
    Assign_HomeworkService ahwService;

    @Autowired
    TeachLectureRepository lectureRepository;

    @Autowired
    Assign_HomeworkRepository ahwRepository;

    @Test
    public void testClassTLR() {
        log.info("Test case : 강좌 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = ahwService.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    @Test
    @Transactional
    public void testSaveHW(){
        boolean testStatus = false;
        Long hwId = 1L;
        String instId = "2017120002";
        String lecCode = "CSE4058";
        Long subCode = 1L;
        String title="테스트 과제1";
        String content="안녕하세요 테스트 과제입니다.";
        LocalDateTime dueDate = LocalDateTime.of(2021,12,10,23,0);
        Boolean isDone = LocalDateTime.now().isAfter(dueDate); // dueDate를 지나가면 isDone True

        AHW_DtoTest ahwDto = new AHW_DtoTest(instId, lecCode, subCode, title, content, dueDate, isDone);

        if(lectureRepository.getCurrentLecture(ahwDto.getInstId(), ahwDto.getLecCode(), ahwDto.getSubCode()).isPresent()){
            Assign_Homework homework = Assign_Homework.builder()
                    .hwId(hwId)
                    .instId(ahwDto.getInstId())
                    .lecCode(ahwDto.getLecCode())
                    .subCode(ahwDto.getSubCode())
                    .title(ahwDto.getTitle())
                    .content(ahwDto.getContent())
                    .dueDate(ahwDto.getDueDate())
                    .isDone(ahwDto.getIsDone()).build();
            ahwRepository.save(homework);
            testStatus = true;
        }
        Assertions.assertTrue(testStatus);
    }


}
