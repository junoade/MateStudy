package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.repository.lecture.Submit_HomeworkRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class Submit_Hw_RepositoryTest {

    @Autowired
    Submit_HomeworkRepository shwRepository;

    @Test
    public void testClass(){
        String proxyName = shwRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    /* 학생이 자신이 제출한 모든 과제 가져오는지 테스트 */

    /* 교수자가 자신의 클래스에서 제출된 모든 과제 가져옴 테스트 */

    /* 학생이 자신의 클래스에서 제출한 모든 과제를 가져옴 테스트 */

    /* 학생이 제출한 특정 과제 hwId를 가져옴 */
}
