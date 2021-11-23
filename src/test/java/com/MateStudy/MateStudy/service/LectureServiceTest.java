package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.service.lecture.LectureService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Slf4j
@SpringBootTest
public class LectureServiceTest {

    @Autowired
    LectureService lectureService;

    /**
     * 최준호
     * Service에 대한 의존성 주입이 잘되는지 테스트
     */
    @Test
    public void testClass() {
        String proxyName = lectureService.getClass().getName();
        Assertions.assertNotNull(proxyName);
    }

    /**
     * 최준호
     * 강좌 학수번호 정보로 갖고 올 수 있는지 확인
     */
    @Transactional
    @Test
    public void testGetLectures() {
        log.info("Test : 학수번호 정보로 갖고 올 수 있는지 확인");
        String lecCode = "CSE4058";
        boolean testStatus = lectureService.isExistByLecCode(lecCode);
        Assertions.assertTrue(testStatus);
    }

    /**
     * 최준호
     * 강좌 학수번호-분반정보로 정보를 갖고 올 수 있는지 확인
     */
    @Transactional
    @Test
    public void testGetLecture() {
        log.info("Test : 학수번호-분반정보로 정보를 갖고 올 수 있는지 확인");
        String lecCode = "CSE4058";
        long subCOde = 1L;
        boolean testStatus = lectureService.isExistByWholeCode(lecCode, subCOde);
        Assertions.assertTrue(testStatus);
    }
}
