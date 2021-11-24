package com.MateStudy.MateStudy.repository.lecture;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 교수자 - 강좌를 맵핑한 Teacing_Lecture에 관한 Spring Data JPA 연동
 */
public interface TeachLectureRepository extends JpaRepository<Teaching_Lecture, String> {

    /* 교수자에게 강좌를 지정하기 위해 강좌 정보를 데이터베이스로부터 가져옴 */
    @Query(value = "SELECT L FROM Lecture L WHERE L.lecCode= :lecCode AND L.subCode = :subCode")
    Optional<Teaching_Lecture> getLectureToAssign(@Param("lecCode") String lecCode, @Param("subCode") Long subCode);

    List<Teaching_Lecture> findByInstId(Member id);
}
