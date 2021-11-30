package com.MateStudy.MateStudy.controller.attendance;

import com.MateStudy.MateStudy.dto.AttendDto;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.Lecture.Submit_HomeworkDto;
import com.MateStudy.MateStudy.dto.MemberDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.service.AttendService;
import com.MateStudy.MateStudy.service.lecture.LectureService;
import com.MateStudy.MateStudy.service.lecture.TakeLectureService;
import com.MateStudy.MateStudy.service.lecture.TeachLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 출석 관리
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class AttendanceController {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private TeachLectureService teachLectureService;

    @Autowired
    private TakeLectureService takeLectureService;

    @Autowired
    private AttendService attendService;


    /* 실습 관리자 출석 관리 */
    @GetMapping("/attendance-admin")
    public String attendAdmin(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 그외 비즈니스 로직 호출 후 Model 객체에 담아서 뷰로 전달 */
        /* 1. 해당 멤버의 담당 과목 - 사실 해당 페이지는 관리자용 페이지라 여기서 검증하는건 두번 검증하는 거긴 하다. */
        List<LectureDto> lectureDtoList = new ArrayList<>();
        List<MemberDto> initMemberDtos = new ArrayList<>();
        String initLecTitle = "";

        if(role.equals("[INSTRUCTOR]")){
            lectureDtoList= teachLectureService.getLectureDtoList(cmDTO.getId());
            /* 초기화면의 관리 학생 화면 */
            log.info(lectureDtoList.toString());
            log.info(lectureDtoList.get(0).toString());
            initLecTitle = lectureDtoList.get(0).getLecTitle();
            String initLecCode = lectureDtoList.get(0).getLecCode();
            Long initSubCode= lectureDtoList.get(0).getSubCode();

            model.addAttribute("initLecCode", initLecCode);
            model.addAttribute("initSubCode", initSubCode);

            initLecTitle += "_"+initSubCode;
            initMemberDtos = teachLectureService.getMyStudents(cmDTO.getId(), initLecCode, initSubCode);
            log.info(initMemberDtos.toString());
        }else if(role.equals("[STUDENT]")){
            lectureDtoList = takeLectureService.getLectureDtoList(cmDTO.getId());
        }

        /* 2. 자신이 관리하는 모든 학생 정보 */


        /* 기본 인적 사항 관련 */
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("role", role);

        /* right-sidebar에서 필요한 담당 과목 정보*/
        model.addAttribute("lectures", lectureDtoList);
        /* 자신이 관리하는 학생들 */
        model.addAttribute("initStudents", initMemberDtos);
        model.addAttribute("initLecTitle", initLecTitle);
        model.addAttribute("lecCode");
        return "attendance/attend-admin";
    }

    @GetMapping("/attendance-student")
    public String attendStudent(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){

        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 그외 비즈니스 로직 호출 후 Model 객체에 담아서 뷰로 전달 */
        /* 사실 해당 페이지는 학생용용 페이지라 여기서 검증하는건 두번 검증하는 거긴 하다.*/
        List<LectureDto> lectureDtoList = new ArrayList<>();
        if(role.equals("[INSTRUCTOR]")){
            lectureDtoList= teachLectureService.getLectureDtoList(cmDTO.getId());
        }else if(role.equals("[STUDENT]")){
            lectureDtoList = takeLectureService.getLectureDtoList(cmDTO.getId());
        }


        /* 기본 인적 사항 관련 */
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("role", role);

        /* right-sidebar에서 필요한 담당 과목 정보*/
        model.addAttribute("lectures", lectureDtoList);
        return "attendance/attend-student";
    }


    @PostMapping("/attend")
    public String setGrade(HttpServletRequest request){
        Long submitId = Long.parseLong(request.getParameter("submitId"));
        String stId = request.getParameter("stId");
        String instId = request.getParameter("instId");
        String lecCode = request.getParameter("lecCode");
        Long subCode = Long.parseLong(request.getParameter("subCode"));
        Long week = Long.parseLong(request.getParameter("week"));

        //Submit_HomeworkDto homework = submit_homeworkService.getHomeworkBySubmitId(submitId);
        Long attedId = attendService.setAttendStatus(stId, instId, lecCode, subCode, week);
        //String grade = Integer.parseInt(request.getParameter("grade"));
        String status = request.getParameter("status");
        //submit_homeworkService.gradeSubmitHw(submitId, grade);
        return "redirect:/attendance-admin/";
    }

}
