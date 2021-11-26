package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.lecture.Taking_Lecture;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.dto.Lecture.TakeLectureDto;
import com.MateStudy.MateStudy.repository.lecture.TakeLectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class TakeLectureRepositoryTest {
    @Autowired
    TakeLectureRepository takeLectureRepository;

    @Autowired
    MemberRepository memberRepository;

    /* 의존성 주입 테스트 */
    @Test
    public void testClassTLR() {
        String proxyName = takeLectureRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    /**
     * 학생에게 강좌에 지정하기 위해 교수자가 존재하는 강좌 정보를 데이터베이스로 부터 조회
     * @see com.MateStudy.MateStudy.service.TeachLectureServiceTest
     * getCurrentLectureTest()에서 작성
     */

    /* 학생의 학번으로 수강하고 있는 과목들 조회 테스트 */
    @Test
    @Transactional
    public void TestFindById(){
        boolean testStatus = false;
        String stId = "2017112095";
        Optional<Member> member = memberRepository.findById(stId);

        try{
            List<Taking_Lecture> myLectures = takeLectureRepository.findByStId(member.get());
            List<TakeLectureDto> dtoList = new ArrayList<>();

            for(Taking_Lecture l : myLectures){
                TakeLectureDto dto = TakeLectureDto.builder()
                        .stId(l.getStId())
                        .instId(l.getInstId())
                        .lecCode(l.getLecCode())
                        .subCode(l.getSubCode())
                        .build();
                dtoList.add(dto);
            }
            testStatus = true;
            log.info(dtoList.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        Assertions.assertTrue(testStatus);

    }
    /* 현재 세션의 학생으로 부터 특정 강좌 분반 찾기 */
    /*@Test
    @Transactional
    public void TestGetStudentLecture(){
        String stId = "2017112095";
        String
    }*/
}
