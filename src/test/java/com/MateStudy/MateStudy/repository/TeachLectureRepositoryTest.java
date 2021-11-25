package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.repository.lecture.TeachLectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
@SpringBootTest
public class TeachLectureRepositoryTest {

    @Autowired
    TeachLectureRepository tlRepository;

    @Test
    public void testClassTLR() {
        log.info("Test case : 강좌 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = tlRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }
    /**
     * 현재 교수자, 학수번호, 분반코드로 데이터가 있는지 조사
     */
    @Test
    @Transactional
    public void getCurrentLectureTest(){
        /* ASSIGN_HW Dto 가정 */
        String instId = "2017120002";
        String lecCode = "CSE4058";
        Long subCode = 1L;
        String title="테스트 과제1";
        String content="안녕하세요 테스트 과제입니다.";
        LocalDateTime dueDate = LocalDateTime.of(2021,12,10,23,0);
        Boolean isDone = false;
        //AHW_DtoTest ahwDto = new AHW_DtoTest(instId, lecCode, subCode, title, content, dueDate, isDone);
        //Optional<Teaching_Lecture> currentLecture = tlRepository.getLectureToAssign("CSE4058", subCode);
        Optional<Teaching_Lecture> currentLecture = tlRepository.getCurrentLecture(instId,"CSE4058", subCode);
        Assertions.assertTrue(currentLecture.isPresent());
    }
}
