package com.MateStudy.MateStudy.controller.attendance;

import com.MateStudy.MateStudy.domain.attendance.Attend;
import com.MateStudy.MateStudy.dto.AttendDto;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.Lecture.Submit_HomeworkDto;
import com.MateStudy.MateStudy.dto.MemberDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.repository.lecture.LectureRepository;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 출석 관리
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class AttendanceController {

    @Autowired
    private TeachLectureService teachLectureService;

    @Autowired
    private TakeLectureService takeLectureService;

    @Autowired
    private AttendService attendService;

    @Autowired
    private LectureRepository lectureRepository;

    /* 실습 관리자 출석 관리 */
    @GetMapping("/attendance-admin")
    public String attendAdmin(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model) {
        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 그외 비즈니스 로직 호출 후 Model 객체에 담아서 뷰로 전달 */
        /* 1. 해당 멤버의 담당 과목 - 사실 해당 페이지는 관리자용 페이지라 여기서 검증하는건 두번 검증하는 거긴 하다. */
        List<LectureDto> lectureDtoList = new ArrayList<>();
        List<MemberDto> initMemberDtos = new ArrayList<>();
        String initLecTitle = "";

        if (role.equals("[INSTRUCTOR]")) {
            lectureDtoList = teachLectureService.getLectureDtoList(cmDTO.getId());
            /* 초기화면의 관리 학생 화면 */
            log.info(lectureDtoList.toString());
            log.info(lectureDtoList.get(0).toString());
            initLecTitle = lectureDtoList.get(0).getLecTitle();
            String initLecCode = lectureDtoList.get(0).getLecCode();
            Long initSubCode = lectureDtoList.get(0).getSubCode();

            model.addAttribute("initLecCode", initLecCode);
            model.addAttribute("initSubCode", initSubCode);

            initLecTitle += "_" + initSubCode;
            initMemberDtos = teachLectureService.getMyStudents(cmDTO.getId(), initLecCode, initSubCode);
            log.info(initMemberDtos.toString());
        } else if (role.equals("[STUDENT]")) {
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

    /* 세부 출석율 */
    @GetMapping("/attendance-admin-detail/{lecCode}/{subCode}")
    public String attendAdminDetail(@AuthenticationPrincipal CustomedMemberDTO cmDTO, @PathVariable String lecCode,
                                    @PathVariable Long subCode, Model model) {
        /*기본 인적사항*/
        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 오르쪽 사이드바 */
        List<LectureDto> lectureDtoList = teachLectureService.getLectureDtoList(cmDTO.getId());

        /* 출석 정보 */
        /*List<AttendDto> attendDtoList = attendService.getMyStudentsAttend(lecCode, subCode);*/
        List<MemberDto> memberDtos = teachLectureService.getMyStudents(cmDTO.getId(), lecCode, subCode);
       /* Map<MemberDto, String> attendInfoMap = new HashMap<>();

        for(int i = 0; i<memberDtos.size(); i++){
            String status =
            attendInfoMap.put(memberDtos.get(i), )
        }*/
        //Map<MemberDto, AttendDto> attendInfoMap = new HashMap<>(memberDtos.size());
        /*for (int i = 0; i < memberDtos.size(); i++) {

            if (attendDtoList.size() != memberDtos.size()) {
                if (attendDtoList.get(i) == null) {
                    AttendDto temp = new AttendDto("미정", 0L, memberDtos.get(i).getId(), id, lecCode, subCode);
                    attendInfoMap.put(memberDtos.get(i), temp);
                }else{
                    if(memberDtos.get(i).getId().equals(attendDtoList.get(i).getStId())){
                        attendInfoMap.put(memberDtos.get(i), attendDtoList.get(i));
                    }else{
                        AttendDto temp = new AttendDto("미정", 0L, memberDtos.get(i).getId(), id, lecCode, subCode);
                        attendInfoMap.put(memberDtos.get(i), temp);
                    }
                }
                continue;
            }
            attendInfoMap.put(memberDtos.get(i), attendDtoList.get(i));
        }*/

        /*Map<MemberDto, List<AttendDto>> attendInfoMap = new HashMap<>();
        for (MemberDto memberDto : memberDtos) {
            String temp_stId = memberDto.getId();
            List<AttendDto> temp = attendService.getMyAttendLog(memberDtos.size(), temp_stId, id, lecCode, subCode);
            attendInfoMap.put(memberDto, temp);
        }
*/
        /* 기본 인적 사항 */
        model.addAttribute("role", role);
        model.addAttribute("id", id);
        model.addAttribute("name", name);

        /* 오른쪽 사이드 바 정보 */
        model.addAttribute("lectures", lectureDtoList);

        /* 과목 정보 */
        String lecTitle = lectureRepository.getLectureTitle(lecCode, subCode);
        model.addAttribute("thisLecTitle", lecTitle);
        model.addAttribute("thisLecCode", lecCode);
        model.addAttribute("thisSubCode", subCode);

        /* 출결 정보 */
        //model.addAttribute("attendMaps", attendInfoMap);
        model.addAttribute("members", memberDtos);
        return "attendance/attend-admin-detail";
    }


    @GetMapping("/attendance-student")
    public String attendStudent(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model) {

        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 그외 비즈니스 로직 호출 후 Model 객체에 담아서 뷰로 전달 */
        /* 사실 해당 페이지는 학생용용 페이지라 여기서 검증하는건 두번 검증하는 거긴 하다.*/
        List<LectureDto> lectureDtoList = new ArrayList<>();
        if (role.equals("[INSTRUCTOR]")) {
            lectureDtoList = teachLectureService.getLectureDtoList(cmDTO.getId());
        } else if (role.equals("[STUDENT]")) {
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
    public String setGrade(HttpServletRequest request) {
        String stId = request.getParameter("stId");
        String instId = request.getParameter("instId");
        String lecCode = request.getParameter("lecCode");
        Long subCode = Long.parseLong(request.getParameter("subCode"));
        Long week = Long.parseLong(request.getParameter("week"));
        String status = request.getParameter("status");
        /*log.info(week.toString());*/
        //Submit_HomeworkDto homework = submit_homeworkService.getHomeworkBySubmitId(submitId);
        Long attendId = attendService.setAttendStatus(stId, instId, lecCode, subCode, week, status);
        //String grade = Integer.parseInt(request.getParameter("grade"));
        //submit_homeworkService.gradeSubmitHw(submitId, grade);

        String rdir = "redirect:/attendance-admin-detail/" + lecCode + "/" + subCode;

        return rdir;
    }

}
