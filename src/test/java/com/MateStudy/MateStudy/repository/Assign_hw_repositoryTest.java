package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.dto.AHW_DtoTest;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.repository.lecture.TeachLectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class Assign_hw_repositoryTest {

    @Autowired
    Assign_HomeworkRepository ahwRepository;

    @Test
    public void testClassAHR() {
        log.info("Test case : 강좌 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = ahwRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    /**
     * 현재 교수자, 학수번호, 분반코드로 등록된 실습과제가 있는지 테스트
     */
    @Test
    @Transactional
    public void testGetAssignedHW() {

        boolean testStatus = false;
        /* 최교수, 소프트웨어공학, 1분반 */
        String instId = "2017120002";
        String lecCode = "CSE4058";
        Long subCode = 1L;
        try {
            List<Assign_Homework> list = ahwRepository.getAssignedHomeworks(instId, lecCode, subCode);
            for (Assign_Homework ahw : list) {
                log.info(ahw.toString());
            }
            testStatus = true;
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            Assertions.assertTrue(testStatus);
        }
    }

}
