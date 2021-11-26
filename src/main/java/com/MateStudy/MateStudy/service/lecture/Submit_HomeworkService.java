package com.MateStudy.MateStudy.service.lecture;

import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.homework.Submit_Homework;
import com.MateStudy.MateStudy.dto.Lecture.Submit_HomeworkDto;
import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.repository.lecture.Submit_HomeworkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 제출한 또는 제출된 과제의 관련된 Service 로직
 * - 과제 등록
 * - 과제 채점
 * - 과제 조회
 * - 과제 삭제
 */
@Slf4j
@Service
public class Submit_HomeworkService {

    @Autowired
    private Submit_HomeworkRepository submit_homeworkRepostiory;

    @Autowired
    private Assign_HomeworkRepository assign_homeworkRepository;

    /**
     * 과제 등록 및 수정 이전에 AssignHomework의 isDone을 체크할 필요가 있음.
     * 따라서 과제 제출전 마감 기한 확인부터 하고 로직 수행
     *
     * @param hwId
     * @return true or false
     */
    @Transactional
    public boolean isDoneHw(Long hwId) {
        Optional<Assign_Homework> hw = assign_homeworkRepository.findById(hwId);
        return hw.isPresent() && hw.get().getIsDone();
    }

    /**
     * 과제 제출 정보를 통해 과제를 제출
     *
     * @return status code
     * 1: success
     * 0: failed since dueDate is over
     * -1: Exception occurs
     * @require hwId가 필요함, 화면단으로부터 작성된 submit_homework 정보
     */
    @Transactional
    public int saveSubmitHwWithId(Submit_HomeworkDto submit_homeworkDto) {
        log.info("saving.. submit homework");
        int status = 0;
        try {
            if (!isDoneHw(submit_homeworkDto.getHwId().getHwId())) {
                submit_homeworkDto.setGrade(0); // 채점 이전에는 과제 점수 grade는 0이 여야함
                submit_homeworkRepostiory.save(submit_homeworkDto.toEntityWithId());
                status = 1;
            } else {
                log.info("마감 기한이 지났습니다.");
            }
        } catch (Exception e) {
            status = -1;
            log.info("invalid submit homework");
            e.printStackTrace();
        }
        return status;
    }

    /**
     * DBMS에게 submitId 생성 위임하여 과제 제출 정보를 통해 과제를 등록
     *
     * @return status
     * 1: success
     * 0: failed since dueDate is over
     * -1: Exception occurs
     * @require hwId가 필요함, 화면단으로부터 작성된 submit_homework 정보
     */
    @Transactional
    public int saveSubmitHwAuto(Submit_HomeworkDto submit_homeworkDto) {
        int status = 0;
        log.info("saving.. submit homework");
        try {
            if (!isDoneHw(submit_homeworkDto.getHwId().getHwId())) {
                submit_homeworkDto.setGrade(0); // 채점 이전에는 과제 점수 grade는 0이 여야함
                submit_homeworkRepostiory.save(submit_homeworkDto.toEntityAuto());
                status = 1;
                log.info("과제 채점 완료");
            } else {
                log.info("마감 기한이 지났습니다.");
            }
        } catch (Exception e) {
            status = -1;
            log.info("invalid submit homework");
            e.printStackTrace();
        }
        return status;
    }

    /**
     * submitId 이용하여 과제 점수 채점
     *
     * @param submitId
     * @param grade
     * @return status code
     * 1 : success in grading on the submitHomework
     * 0 : failed
     * -1 : exception
     */
    @Transactional
    public int gradeSubmitHw(Long submitId, Integer grade) {
        int status = 0;
        if (grade < 0) {
            return status;
        }
        Optional<Submit_Homework> submit_homework = submit_homeworkRepostiory.findById(submitId);
        try {
            if (submit_homework.isPresent()) {
                submit_homework.get().setGrade(grade);
                submit_homeworkRepostiory.save(submit_homework.get());
                status = 1;
            }
            log.info("과제 채점 완료");
        } catch (Exception e) {
            status = -1;
            log.info("채점 오류");
            e.printStackTrace();
            /*
            // TODO 화면단까지 오류 던저주기 고민
            throw e;
            */
        }

        return status;
    }

    /**
     * 화면단으로 부터 전달받은 Submit_HomworkDto를 이용하여 과제 점수 채점
     *
     * @param submit_homeworkDto
     * @param grade
     * @return status
     * 1 : success in grading on the submitHomework
     * 0 : failed
     * -1 : exception
     */
    @Transactional
    public int gradeSubmitHw(Submit_HomeworkDto submit_homeworkDto, Integer grade) {
        int status = 0;
        if (grade < 0) {
            return status;
        }
        try {
            submit_homeworkDto.setGrade(grade);
            if (submit_homeworkDto.getSubmitId() == null) {
                submit_homeworkRepostiory.save(submit_homeworkDto.toEntityAuto());
            } else {
                submit_homeworkRepostiory.save(submit_homeworkDto.toEntityWithId());
            }
            status = 1;
            log.info("과제 채점 완료");
        } catch (Exception e) {
            status = -1;
            log.info("채점 오류");
            e.printStackTrace();
            // TODO 화면단까지 오류 던저주기 고민
            throw e;
        }
        return status;

    }

    /* 학생 자신이 제출한 모든 과제 리스트 반환 */
    @Transactional
    public List<Submit_HomeworkDto> getAllHomeworks(String stId) {
        List<Submit_Homework> list = submit_homeworkRepostiory.getEverySubmitHw(stId);
        return getSubmit_homeworkDtos(list);
    }

    /* 특정 강좌에 대한 학생의 모든 과제 리스트 반환 */
    @Transactional
    public List<Submit_HomeworkDto> getLectureHomeworks(String instId, String lecCode, Long subCode) {
        List<Submit_Homework> list = submit_homeworkRepostiory.getEveryLectureSubmits(instId, lecCode, subCode);
        return getSubmit_homeworkDtos(list);
    }

    /* 학생이 특정 강좌에 제출한 모든 과제 가져옴 */
    @Transactional
    public List<Submit_HomeworkDto> getMyLectureHw(String stId, String instId, String lecCode, Long subCode) {
        List<Submit_Homework> list = submit_homeworkRepostiory.getMyLectureHw(stId, instId, lecCode, subCode);
        return getSubmit_homeworkDtos(list);
    }

    /* Dto의 List 구하는 중복 코드 제거용 */
    private List<Submit_HomeworkDto> getSubmit_homeworkDtos(List<Submit_Homework> list) {
        List<Submit_HomeworkDto> result = new ArrayList<>();
        for (Submit_Homework l : list) {
            Submit_HomeworkDto dto = new Submit_HomeworkDto(l.getStId(), l.getInstId(), l.getLecCode(), l.getSubCode(),
                    l.getHwId(), l.getTitle(), l.getContent(), l.getGrade());
            result.add(dto);
        }
        return result;
    }

    /* 학생이 제출한 특정 과제 hwId를 가져옴 */
    public Submit_HomeworkDto getMySubmit(String stId, Long hwId) {
        Optional<Submit_Homework> submit = submit_homeworkRepostiory.getMySubmit(stId, hwId);
        if (submit.isPresent()) {
            Submit_HomeworkDto result = new Submit_HomeworkDto(submit.get().getSubmitId(), submit.get().getStId(), submit.get().getInstId(),
                    submit.get().getLecCode(), submit.get().getSubCode(), submit.get().getHwId(),
                    submit.get().getTitle(), submit.get().getContent(), submit.get().getGrade());
            return result;
        }
        return null;
        /*try{}
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }*/
    }

    /* 과제 등록한 내용 삭제 */
}
