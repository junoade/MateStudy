package com.MateStudy.MateStudy.controller;

import com.MateStudy.MateStudy.dto.HomeworkDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;

    @GetMapping("/homework")
    public String homework(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        if(cmDTO.getAuthorities().toString().equals("[ADMIN]")){
            return homeWorkAdmin(cmDTO, model);

        }else{
            return homeWorkStudent(cmDTO, model);
        }
    }

    public String homeWorkAdmin(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        model.addAttribute("name",cmDTO.getName());
        return "homework-admin";
    }

    public String homeWorkStudent(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        return "homework-student";
    }

    @GetMapping("hw-admin")
    public String hwAdminTest(Model model){
        List<HomeworkDto> homeworkDtoList = homeworkService.getHomeworkList();
        model.addAttribute("postList", homeworkDtoList);
        return "/homework/hw-admin";
    }

    @GetMapping("hw-student")
    public String hwStudentTest(){
        return "/homework/hw-student";
    }
}
