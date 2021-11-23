package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.repository.lecture.LectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
public class LectureRepositoryTest {
    @Autowired
    LectureRepository lectureRepository;

    /**
     * 최준호
     * 강좌 도메인과 관련하여 JPA Repositroy를 상속받은 UserRepositroy의 의존성 주입이 잘 되는지 테스트
     */
    @Test
    public void testClass() {
        log.info("Test case : 강좌 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = lectureRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    /**
     * 최준호
     * 강좌 정보 CREATE
     */
    @Test
    @Transactional
    public void testInsertLecture() {
        log.info("INSERT DUMMY MEMBER INTO THE DATABASE");
        boolean testStatus = false;
        try {
            final int SIZE = 5;
            String[] dummyLecCode = {"CSE4058", "CSE4058", "CSE4036", "CSE4036", "CSE4041", "CSE4041", "CSE4038", "CSE4038"};
            long[] dummySubCode = {1L, 2L, 1L, 2L, 1L, 2L, 1L, 2L,};
            String[] dummyTitle = {"소프트웨어공학개론", "소프트웨어공학개론", "인공지능", "인공지능",
                    "데이터베이스프로그래밍", "데이터베이스프로그래밍", "데이터통신입문", "데이터통신입문"};
            for (int i = 0; i < SIZE; i++) {
                Lecture lecture = Lecture.builder().lecCode(dummyLecCode[i]).subCode(dummySubCode[i])
                        .lecTitle(dummyTitle[i]).build();
                lectureRepository.save(lecture);
            }
            log.info("DONE");
            testStatus = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assertions.assertTrue(testStatus);
        }
    }
}
