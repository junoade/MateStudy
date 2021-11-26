package com.MateStudy.MateStudy.repository.lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.lecture.Taking_Lecture;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Member의 id와 TEACH_LECUTRE의 학수번호, 분반 정보를 바탕으로 TAKE_LECTURE에 INSERT (강의에 학생 추가)
 * Member의 id와 TEACH_LECUTRE의 학수번호, 분반 정보를 바탕으로 TAKE_LECUTURE로부터 특정 학생 반환 (조회)
 * 현재 TAKE_LECTURE에 있는 instId를 바탕으로 stId를 조회하여 멤버의 정보 반환 해주기 (강의내 전체 학생 조회)
 *
 */
public interface TakeLectureRepository extends JpaRepository<Taking_Lecture, String> {
    /* 학생에게 강좌를 지정하기 위해 교수자가 존재하는 강좌 정보를 데이터베이스로부터 가져오는건 TeachLectureRepository에 있다 */

    /* 학생의 ID(학번)으로 부터 현재 맵핑된 실습 강좌 (TAKE_LECTURE의 엔티티 리스트 반환 */
    List<Taking_Lecture> findByStId(Member id);

    /* 현재 세션의 학생으로부터 특정 강좌 분반 찾기 */
    @Query(value= "SELECT L FROM Taking_Lecture L WHERE L.stId = :stId AND L.instId= :instId AND L.lecCode = :lecCode AND L.subCode = :subCode")
    Optional<Taking_Lecture> getStudentLecture(@Param("stId") String stId, @Param("instId") String instId, @Param("lecCode") String lecCode, @Param("subCode") Long subCode);
}
