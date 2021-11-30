package com.MateStudy.MateStudy.repository.qna;

import com.MateStudy.MateStudy.domain.question.Question;
import com.MateStudy.MateStudy.domain.question.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query(value = "SELECT H FROM Reply H WHERE H.qno = :qno")
    List<Reply> getReplyByQno(@Param("qno") Question qno);
}
