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
}
