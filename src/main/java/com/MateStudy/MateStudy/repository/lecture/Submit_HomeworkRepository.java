package com.MateStudy.MateStudy.repository.lecture;

import com.MateStudy.MateStudy.domain.homework.Submit_Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 제출한 과제들에 대한 레파지토리
 */
public interface Submit_HomeworkRepository extends JpaRepository<Submit_Homework, Long> {

    /* 학생 자신이 제출한 모든 과제 가져옴 */
    @Query(value="SELECT S FROM Submit_Homework S WHERE S.stId = :stId")
    List<Submit_Homework> getEverySubmitHw(@Param("stId") String stId);

    /* 교수자가 자신의 클래스에서 학생들이 제출한 모든 과제 조회 */
    @Query(value="SELECT S FROM Submit_Homework S WHERE S.instId = :instId AND S.lecCode = :lecCode AND S.subCode = :subCode")
    List<Submit_Homework> getEveryLectureSubmits(@Param("instId") String instId, @Param("lecCode") String lecCode, @Param("subCode") Long subCode);

    /* 학생이 특정 강좌에 제출한 모든 과제 가져옴 */
    @Query(value="SELECT S FROM Submit_Homework S WHERE S.stId = :stId  AND S.instId = :instId AND S.lecCode = :lecCode" +
            " AND S.subCode = :subCode")
    List<Submit_Homework> getMyLectureHw(@Param("stId") String stId, @Param("instId") String instId, @Param("lecCode") String lecCode, @Param("subCode") Long subCode);


    /* 학생이 제출한 특정 과제 hwId를 가져옴 */
    @Query(value="SELECT S FROM Submit_Homework S WHERE S.stId = :stIds AND S.hwId = :hwId")
    Optional<Submit_Homework> getMySubmit (@Param("stId") String stId, @Param("hwId") Long hwId);


    /* 학생이 특정 강좌의 hwId에 과제를 INSERT */
    /* save 이용하기 */

    /* 학생이 특정 강좌의 hwId에 올린 특정 submitId를 UPDATE */
    /* save 이용하기 */

    /* 학생이 특정 강좌의 hwId에 올린 과제를 삭제 */
    /* deleteBy 이용하기 */
}