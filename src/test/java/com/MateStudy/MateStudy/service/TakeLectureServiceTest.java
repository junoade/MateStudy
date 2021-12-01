package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.dto.Lecture.TakeLectureDto;
import com.MateStudy.MateStudy.repository.MemberRepository;
import com.MateStudy.MateStudy.repository.lecture.TakeLectureRepository;
import com.MateStudy.MateStudy.service.lecture.TakeLectureService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
public class TakeLectureServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TakeLectureService takeLectureService;

    /* 의존성 주입 테스트 */
    @Test
    public void testClassTLS() {
        String proxyName = takeLectureService.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    @Test
    public void testClassMR() {
        String proxyName = memberRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    /* 학생을 개설된 강좌에 배정 테스트 */
    @Test
    @Transactional
    public void testSetStudent() {
        boolean testStatus = false;
        //String[] dummyIdInfo = {"2017112095", "2016112144", "2017112129", "2017112083"};
        String[] dummyIdInfo = {"2017110169", "2017119308", "2017113511", "2017110140"};

        String instId = "2017120002";
        String lecCode = "CSE2017";
        Long subCode = 1L;
        try {
            for (String s : dummyIdInfo) {
                takeLectureService.setStudent(s, instId, lecCode, subCode);
            }
            testStatus = true;
            log.info("강좌에 학생이 등록되었습니다");
        } catch (Exception e) {

            e.printStackTrace();
        }
        TestTransaction.flagForCommit();
        Assertions.assertTrue(testStatus);
    }

    /* 학생이 수강하는 과목을 모두 반환 */
    @Test
    @Transactional
    public void testGetTakeLectureList() {
        String stId = "2017112095";
        List<TakeLectureDto> dtos = takeLectureService.getTakeLectureList(stId);
        for (TakeLectureDto dto : dtos) {
            log.info(dto.getStId().getName() +" "+ dto.getStId().getId() + " " +dto.getInstId() +" " + dto.getLecCode() + " " + dto.getSubCode());
        }
    }

}
