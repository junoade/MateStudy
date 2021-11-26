package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.repository.MemberRepository;
import com.MateStudy.MateStudy.repository.lecture.LectureRepository;
import com.MateStudy.MateStudy.repository.lecture.TeachLectureRepository;
import com.MateStudy.MateStudy.service.lecture.TeachLectureService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.TestTransaction;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class TeachLectureServiceTest {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeachLectureRepository teachingRepository;


    @Test
    public void testLectureClass() {
        log.info("Test case : Lecture 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = lectureRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    @Test
    public void testMemberClass() {
        log.info("Test case : Member 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = memberRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    @Test
    public void testTeachingClass() {
        log.info("Test case : Member 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = teachingRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    /* 강좌에 교수자 임명 테스트케이스 */
    @Test
    @Transactional
    public void testSetInstructor(){
        boolean testStatus = false;
        Optional<Lecture> lecture = lectureRepository.getOneLecture("CSE4058", 1L);
        log.info(lecture.toString());
        Optional<Member> instructor = memberRepository.findById("2017120002");
        log.info(instructor.toString());
        if(lecture.isPresent() && instructor.isPresent()){
            Teaching_Lecture result = Teaching_Lecture.builder()
                    .instId(instructor.get())
                    .lecCode(lecture.get().getLecCode())
                    .subCode(lecture.get().getSubCode())
                    .build();
            teachingRepository.save(result);
            testStatus = true;
        }else{
            log.info("강좌에 교수자 지정 실패");
        }
        /**
         * 현재 성능을 위해 데이터베이스 fetch 모드가 Lazy인데 여러 repository에 걸친 질의의 경우,
         * 하나의 Transcation으로 처리하기 위해 @Transional 필요
         * 이때 테스트케이스라면, commit; 하지않고 rollback; 하여 명시적으로 아래에 코드 작성함.
         */
        //TestTransaction.flagForCommit();
        Assertions.assertTrue(testStatus);
    }

}
