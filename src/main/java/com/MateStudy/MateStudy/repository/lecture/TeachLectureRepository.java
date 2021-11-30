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
 * 테이블 MEMBER(특히 교수자) - LECTURE 사이의 M:N 관계 해소
 * @see Teaching_Lecture
 * @see Member
 * @see com.MateStudy.MateStudy.domain.lecture.Lecture
 */
public interface TeachLectureRepository extends JpaRepository<Teaching_Lecture, String> {

    /* 교수자에게 강좌를 지정하기 위해 강좌 정보를 데이터베이스로부터 가져옴 */
    @Query(value = "SELECT L FROM Lecture L WHERE L.lecCode= :lecCode AND L.subCode = :subCode")
    Optional<Teaching_Lecture> getLectureToAssign(@Param("lecCode") String lecCode, @Param("subCode") Long subCode);

    /* 교수자의 ID(학번)로부터 현재 맵핑된 실습(강좌), TEACH_LECTURE 엔티티의 리스트 반환 */
    List<Teaching_Lecture> findByInstId(Member id);

    /**
     * 현재 세션의 교수자의 정보로 부터 특정 강좌, 분반 찾기
     * TODO 1126 쿼리문 개선할 수 있지 않을까?
     */
    @Query(value = "SELECT L FROM Member M, Teaching_Lecture L WHERE M.id = L.instId AND M.id = :instId AND L.lecCode= :lecCode AND L.subCode = :subCode")
    Optional<Teaching_Lecture> getCurrentLecture(@Param("instId") String instId, @Param("lecCode") String lecCode, @Param("subCode") Long subCode);

    @Query(value = "SELECT L FROM Teaching_Lecture L WHERE L.lecCode= :lecCode AND L.subCode = :subCode")
    Optional<Teaching_Lecture> getTeachLecture(@Param("lecCode") String lecCode, @Param("subCode") Long subCode);
}
