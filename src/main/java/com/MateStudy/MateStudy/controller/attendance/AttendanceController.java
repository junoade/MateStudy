package com.MateStudy.MateStudy.controller.attendance;

import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
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


    /* 실습 관리자 출석 관리 */
    @GetMapping("/attendance-admin")
    public String attendAdmin(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 그외 비즈니스 로직 호출 후 Model 객체에 담아서 뷰로 전달 */
        /* 1. 해당 멤버의 담당 과목 - 사실 해당 페이지는 관리자용 페이지라 여기서 검증하는건 두번 검증하는 거긴 하다. */
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

}
