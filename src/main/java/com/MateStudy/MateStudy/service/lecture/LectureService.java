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

    @Transactional
    public boolean isExistByWholeCode(String lectureCode, long subCode){
        log.info("> isLectureExist? ");
        Optional<Lecture> lecture = lecRepository.getOneLecture(lectureCode, subCode);
        return lecture.isPresent();
    }

    public LectureDto getLecture(String lecCode, Long subCode){
        Optional<Lecture> lecture = lecRepository.getOneLecture(lecCode, subCode);
        LectureDto lectureDto = LectureDto.builder()
                .lecCode(lecture.get().getLecCode())
                .subCode(lecture.get().getSubCode())
                .lecTitle(lecture.get().getLecTitle())
                .build();
        return lectureDto;
    }

    public void saveLecture(LectureDto lectureDto){
        lecRepository.save(lectureDto.toEntity());
    }
}
