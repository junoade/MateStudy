package com.MateStudy.MateStudy.repository.lecture;

import com.MateStudy.MateStudy.domain.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, String> {

    /* 학수번호-분반정보를 통해 현재 등록된 실습 정보가 있는지 반환 */
    @Query(value="SELECT DISTINCT L FROM Lecture L WHERE L.lecCode = :lecCode")
    List<Lecture> getLectures(@Param("lecCode") String lecCode);


    /* 학수번호-분반정보를 통해 지정하기 위해 강좌 정보를 데이터베이스로부터 반환*/
    @Query(value = "SELECT L FROM Lecture L WHERE L.lecCode= :lecCode AND L.subCode = :subCode")
    Optional<Lecture> getOneLecture(@Param("lecCode") String lecCode, @Param("subCode") Long subCode);
}
