package com.MateStudy.MateStudy.service.lecture;

import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.repository.MemberRepository;
import com.MateStudy.MateStudy.repository.lecture.LectureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class LectureService {

    @Autowired
    private LectureRepository lecRepository;

    /* 학수번호, 분반정보로 강의 찾기
    * Lecture 엔티티는 fetch 방법이 LazyType이라 Transactional로 데이터베이스 연결 이후에도 접근하게끔
    *  */
    @Transactional
    public boolean isExistByLecCode(String lectureCode){
        log.info("> isLecturesExist? ");
        List<Lecture> list = lecRepository.getLectures(lectureCode);
        return !list.isEmpty();
    }

    /* 학수번호 - 분반코드를 통해 존재하는 강좌인지 먼저 확인하는 API 제공 */
    @Transactional
    public boolean isExistByWholeCode(String lectureCode, long subCode){
        log.info("> isLectureExist? ");
        Optional<Lecture> lecture = lecRepository.getOneLecture(lectureCode, subCode);
        return lecture.isPresent();
    }

    /* 학수번호 - 분반코드를 통해 존재하는 강좌라면 주요 정보 반환 */
    public LectureDto getLecture(String lecCode, Long subCode){
        if(!isExistByWholeCode(lecCode, subCode)){
            Optional<Lecture> lecture = lecRepository.getOneLecture(lecCode, subCode);
            LectureDto lectureDto = LectureDto.builder()
                    .lecCode(lecture.get().getLecCode())
                    .subCode(lecture.get().getSubCode())
                    .lecTitle(lecture.get().getLecTitle())
                    .build();
            return lectureDto;
        }else{
            log.info(">> THERE IS NO LECTURE searched by "+ lecCode +" "+ subCode+ ", problems in LectureService.java ");
            return null;
        }
    }
    /* 사용자가 폼으로 입력한 LectureDto 로부터 Lecture 레파지토리에 저장함 */
    public void saveLecture(LectureDto lectureDto){
        if(!isExistByWholeCode(lectureDto.getLecCode(), lectureDto.getSubCode())){
            lecRepository.save(lectureDto.toEntity());
        }else{
            log.info(">> Already Existed Lecture, " + lectureDto.toString() +", problems in LectureService.java");
        }
    }
}
