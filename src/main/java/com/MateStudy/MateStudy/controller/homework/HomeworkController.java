package com.MateStudy.MateStudy.controller.homework;

import com.MateStudy.MateStudy.domain.lecture.Teaching_Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.service.lecture.Assign_HomeworkService;
import com.MateStudy.MateStudy.service.lecture.LectureService;
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

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeworkController {
    @Autowired
    private Assign_HomeworkService assgin_homeworkService;

    @Autowired
    private TeachLectureService teachLectureService;

    @Autowired
    private LectureService lectureService;

    @GetMapping("/homework")
    public String homework(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        if(cmDTO.getAuthorities().toString().equals("[INSTRUCTOR]")){
            return homeworkAdmin(cmDTO, model);
        }else{
            return homeworkStudent(cmDTO, model);
        }
    }

    @GetMapping("/homework-admin")
    public String homeworkAdmin(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        model.addAttribute("name",cmDTO.getName());
        return "/homework/homework-admin";
    }

    @GetMapping("/homework-student")
    public String homeworkStudent(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        model.addAttribute("name",cmDTO.getName());
        return "/homework/homework-student";
    }

    @GetMapping("/homework/register/{lecCode}/{subCode}")
    public String homeworkRegisterPage(@AuthenticationPrincipal CustomedMemberDTO cmDTO,
                                   @PathVariable String lecCode, @PathVariable Long subCode,
                                   Model model){

        LectureDto lectureDto = lectureService.getLecture(lecCode, subCode);
        model.addAttribute("instId",cmDTO.getId());
        model.addAttribute("lecture",lectureDto);
        return "/homework/homework-register";
    }

    @PostMapping("/homework/register")
    public String homeworkRegister(Assign_HomeworkDto assignHomeworkDto){
        assgin_homeworkService.saveHomework(assignHomeworkDto);
        return "redirect:/main";
    }
}
