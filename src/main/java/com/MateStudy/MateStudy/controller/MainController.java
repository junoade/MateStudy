package com.MateStudy.MateStudy.controller;


import com.MateStudy.MateStudy.domain.common.DateBefore;
import com.MateStudy.MateStudy.domain.common.DateComparator;
import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.MemberDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.service.AccountService;
import com.MateStudy.MateStudy.service.lecture.LectureService;
import com.MateStudy.MateStudy.service.lecture.TakeLectureService;
import com.MateStudy.MateStudy.service.lecture.TeachLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TeachLectureService teachLectureService;

    @Autowired
    private TakeLectureService takeLectureService;

    @Autowired
    private LectureService lectureService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    private DateComparator dateComparator = new DateComparator();

    /* 로그인시 데이터베이스의 Member를 스프링 시큐리티가 인가할 CustomedMemberDTO로 전환 고려*/
    @GetMapping("/main")
    public String main(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model) {
        log.info("---------------------------------------------------------------------");
        log.info("System : Request GetMapping on /main");
        log.info(cmDTO.toString());
        log.info(cmDTO.getAuthorities().toString());
        String role = cmDTO.getAuthorities().toString();

        List<LectureDto> lectureDtoList = new ArrayList<>();
        if(role.equals("[INSTRUCTOR]")){
             lectureDtoList= teachLectureService.getLectureDtoList(cmDTO.getId());
        }else if(role.equals("[STUDENT]")){
            lectureDtoList = takeLectureService.getLectureDtoList(cmDTO.getId());
        }

        List<Pair<Assign_HomeworkDto, Optional<Lecture>>> ahdList = new ArrayList<>();
        for(LectureDto lDto : lectureDtoList){
            List<Pair<Assign_HomeworkDto, Optional<Lecture>>> addList = new ArrayList<>();
            addList = lectureService.getHomeworkByCodeWithLecture(lDto.getLecCode(),lDto.getSubCode());
            ahdList.addAll(addList);
        }

        Collections.sort(ahdList, dateComparator);

        List<String> remain = DateBefore.getIntervalPair(ahdList);

        model.addAttribute("name",cmDTO.getName());
        model.addAttribute("role",role);
        model.addAttribute("allHomeworkList",ahdList);
        model.addAttribute("lectures",lectureDtoList);
        model.addAttribute("remainDate",remain);
        return "/main";
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }

    @GetMapping("/member/login")
    public String login(){
        return "index";
    }

    @GetMapping("/member/signUp")
    public String signUpPage(){
        return "signUp";
    }

    @PostMapping("/member/signUp")
    public String signUp(MemberDto memberDto){
        accountService.signUp(memberDto);
        return "redirect:../main";
    }

    @GetMapping("/member/password")
    public String passwordPage() {
        return "/password";
    }

    @PostMapping("/member/password")
    public String password(String id, String password){
        accountService.modify(id,password);
        return "redirect:../main";
    }

    @GetMapping("/member/denied")
    public String denied(){
        return "denied";
    }

    @GetMapping("/homework/grade")
    public String grade(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        model.addAttribute("name",cmDTO.getAuthorities());
        return "homework/grade";
    }

    // UI TEST용으로 만들었어요. 맘대로 바꾸세요
    @GetMapping("qna")
    public String qna() {
        return "qna";
    }

    // UI TEST용으로 만들었어요. 맘대로 바꾸세요
    @GetMapping("page2")
    public String page2() {
        return "page2";
    }

}
