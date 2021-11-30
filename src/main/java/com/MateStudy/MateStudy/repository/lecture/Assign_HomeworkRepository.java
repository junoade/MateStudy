package com.MateStudy.MateStudy.repository.lecture;

import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 진행 중인 특정 강좌에 교수자가 과제들을 올리는 과제들의 Repository
 * @see Assign_Homework
 */
public interface Assign_HomeworkRepository extends JpaRepository<Assign_Homework, Long> {

    /* 교수자, 학수번호, 분반 정보를 통해 해당 강좌에 부여된 모든 실습 코드를 가져옴 */
    @Query(value="SELECT H FROM Assign_Homework H WHERE H.instId = :instId AND H.lecCode = :lecCode AND H.subCode = :subCode")
    List<Assign_Homework> getAssignedHomeworks(@Param("instId") String instId, @Param("lecCode") String lecCode, @Param("subCode") Long subCode);

    @Query(value="SELECT H FROM Assign_Homework H WHERE H.lecCode = :lecCode AND H.subCode = :subCode")
    List<Assign_Homework> getAssignedHomeworksByCode(@Param("lecCode") String lecCode, @Param("subCode") Long subCode);

    @Query(value="SELECT COUNT(H.hwId) FROM Assign_Homework H WHERE H.isDone=true")
    Long getCountDoneHw();

    /* 마감된 과목의 제출 학생 수 반환 */
    @Query(value="SELECT COUNT(S.submitId) FROM Submit_Homework S, Assign_Homework A WHERE S.hwId=A.hwId AND A.hwId = :hwId AND A.isDone = true", nativeQuery = true)
    Long getCountSumbitFromDoneHw(@Param("hwId") String hwId);

    @Query(value="SELECT COUNT(S.submitId) FROM Submit_Homework S, Assign_Homework A WHERE S.hwId=A.hwId AND A.hwId = :hwId AND A.isDone = false", nativeQuery = true)
    Long getCountNotSubmitHw(@Param("hwId") String hwId);
}
