package com.MateStudy.MateStudy.repository.qna;

import com.MateStudy.MateStudy.domain.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value="SELECT H FROM Question H WHERE H.lecCode = :lecCode AND H.subCode = :subCode")
    List<Question> getQuestionByCode(@Param("lecCode") String lecCode, @Param("subCode") Long subCode);
}
