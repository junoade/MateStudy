package com.MateStudy.MateStudy.service.qna;

import com.MateStudy.MateStudy.domain.common.DateBefore;
import com.MateStudy.MateStudy.domain.question.Question;
import com.MateStudy.MateStudy.domain.question.Reply;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.qna.QuestionDto;
import com.MateStudy.MateStudy.dto.qna.ReplyDto;
import com.MateStudy.MateStudy.repository.qna.QuestionRepository;
import com.MateStudy.MateStudy.repository.qna.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QnaService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public Question saveQuestion(QuestionDto questionDto){
        Question question = new Question();
        question = questionRepository.save(questionDto.toEntity());
        return question;
    }

    @Transactional
    public Reply saveReply(ReplyDto replyDto){
        Reply reply = new Reply();
        reply = replyRepository.save(replyDto.toEntity());
        return reply;
    }

    @Transactional
    public List<QuestionDto> getQuestionByCode(String lecCode, Long subCode){
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> questions = questionRepository.getQuestionByCode(lecCode, subCode);
        for(Question q : questions){
            QuestionDto qDto = QuestionDto.builder()
                    .qno(q.getQno())
                    .stId(q.getStId())
                    .lecCode(q.getLecCode())
                    .subCode(q.getSubCode())
                    .title(q.getTitle())
                    .content(q.getContent())
                    .instId(q.getInstId())
                    .remainDate(DateBefore.getIntervalDate(q.getRegDate()))
                    .build();
            questionDtoList.add(qDto);
        }
        return questionDtoList;
    }

    @Transactional
    public List<QuestionDto> getAllQuestions(List<LectureDto> lectureDtoList){
        List<QuestionDto> questionList= new ArrayList<>();
        for(LectureDto l : lectureDtoList){
            List<QuestionDto> questions = getQuestionByCode(l.getLecCode(),l.getSubCode());
            questionList.addAll(questions);
        }
        return questionList;
    }
}
