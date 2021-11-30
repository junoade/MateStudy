package com.MateStudy.MateStudy.service.qna;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.common.DateBefore;
import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.domain.question.Question;
import com.MateStudy.MateStudy.domain.question.Reply;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.qna.QuestionDto;
import com.MateStudy.MateStudy.dto.qna.ReplyDto;
import com.MateStudy.MateStudy.repository.MemberRepository;
import com.MateStudy.MateStudy.repository.lecture.LectureRepository;
import com.MateStudy.MateStudy.repository.qna.QuestionRepository;
import com.MateStudy.MateStudy.repository.qna.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QnaService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LectureRepository lectureRepository;


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
            Optional<Member> student = memberRepository.findById(q.getStId());
            Optional<Member> instructor = memberRepository.findById(q.getInstId());
            Optional<Lecture> lecture = lectureRepository.getOneLecture(lecCode, subCode);

            QuestionDto qDto = QuestionDto.builder()
                    .qno(q.getQno())
                    .stId(q.getStId())
                    .lecCode(q.getLecCode())
                    .subCode(q.getSubCode())
                    .title(q.getTitle())
                    .content(q.getContent())
                    .instId(q.getInstId())
                    .remainDate(DateBefore.getIntervalDateAfter(q.getRegDate()))
                    .stName(student.get().getName())
                    .instName(instructor.get().getName())
                    .lecTitle(lecture.get().getLecTitle())
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

    @Transactional
    public List<ReplyDto> getAllReply(List<QuestionDto> questionDtoList){
        List<ReplyDto> result = new ArrayList<>();
        for(QuestionDto qDto : questionDtoList){
            Optional<Question> question = questionRepository.findById(qDto.getQno());
            List<Reply> replyList= replyRepository.getReplyByQno(question.get());
            for(Reply r : replyList){
                ReplyDto rD = ReplyDto.builder()
                        .qno(qDto.getQno())
                        .stName(qDto.getStName())
                        .instName(qDto.getInstName())
                        .date(DateBefore.getIntervalDateAfter(r.getRegDate()))
                        .title(r.getTitle())
                        .content(r.getContent())
                        .build();
                result.add(rD);
            }
        }
        return result;
    }
}
