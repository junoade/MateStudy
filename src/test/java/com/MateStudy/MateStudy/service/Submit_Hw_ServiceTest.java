package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.repository.lecture.Submit_HomeworkRepository;
import com.MateStudy.MateStudy.service.lecture.Submit_HomeworkService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class Submit_Hw_ServiceTest {

    @Autowired
    Submit_HomeworkService shwService;

    @Autowired
    private Assign_HomeworkRepository assign_homeworkRepository;

    @Autowired
    private Submit_HomeworkRepository submit_homeworkRepostiory;

    /* 의존성 주입 테스트 */
    @Test
    public void testClassSHS() {
        String proxyName = shwService.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    @Test
    public void testClassAHR() {
        String proxyName = assign_homeworkRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    @Test
    public void testClassSHR() {
        String proxyName = submit_homeworkRepostiory.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }
    /* TODO */
    /* 과제 마감 여부 확인 */
    /*- 과제 제출 정보를 이용한 과제 제출*/
    /* - 과제 점수 채점 */
    /* - 과제 리스트 반환 관련 */
    /* - 특정 과제 hwId를 이용하여 학생이 제출한 특정 제출물 조회관련 */
}
