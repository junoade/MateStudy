package com.MateStudy.MateStudy.controller.QnA;

import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.qna.QuestionDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class QnaController {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private TeachLectureService teachLectureService;

    @Autowired
    private TakeLectureService takeLectureService;

    /* 전체 질문 조회 컨셉 */
    @GetMapping("/qna")
    public String qna(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model) {

        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 그외 비즈니스 로직 호출 후 Model 객체에 담아서 뷰로 전달 */
        /* 1. 해당 멤버의 담당 과목 */
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

        return "qna/qna";
    }

    /* 역할에 따른 과목별 질문 관련*/
    @GetMapping("/qna-admin/{lecCode}/{subCode}")
    public String qnaAdminDetail(@AuthenticationPrincipal CustomedMemberDTO cmDTO,  @PathVariable String lecCode,
                                 @PathVariable Long subCode, Model model){
        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 그외 비즈니스 로직 호출 후 Model 객체에 담아서 뷰로 전달 */
        /* 1. 해당 멤버의 담당 과목 */
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

        /* 링크용 */
        model.addAttribute("lecCode", lecCode);
        model.addAttribute("subCode", subCode);
        return "/qna/qna-admin";
    }

    @GetMapping("/qna-student/{lecCode}/{subCode}")
    public String qnaStudentDetail(@AuthenticationPrincipal CustomedMemberDTO cmDTO,  @PathVariable String lecCode,
                                   @PathVariable Long subCode, Model model){
        String role = cmDTO.getAuthorities().toString();
        String name = cmDTO.getName();
        String id = cmDTO.getId();

        /* 그외 비즈니스 로직 호출 후 Model 객체에 담아서 뷰로 전달 */
        /* 1. 해당 멤버의 담당 과목 */
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

        /* 링크용 */
        model.addAttribute("lecCode", lecCode);
        model.addAttribute("subCode", subCode);

        return "/qna/qna-student";
    }

    @GetMapping("/qna/register/{lecCode}/{subCode}")
    public String qnaRegisterPage(@AuthenticationPrincipal CustomedMemberDTO cmDTO, @PathVariable String lecCode,
                                  @PathVariable Long subCode, Model model){
        String role = cmDTO.getAuthorities().toString();

        LectureDto lectureDto = lectureService.getLecture(lecCode, subCode);
        if(role.equals("[INSTRUCTOR]")){
           model.addAttribute("instId", cmDTO.getId());
        }else if(role.equals("[STUDENT]")){
            model.addAttribute("stId", cmDTO.getId());
        }

        model.addAttribute("lecture", lectureDto);

        return "/qna/qna-register";

    }


    @PostMapping("/qna/register")
    public String qnaRegister(QuestionDto qnaDto){




        return "redirect:/qna-student/";
    }

}
