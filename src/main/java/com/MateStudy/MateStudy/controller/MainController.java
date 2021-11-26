package com.MateStudy.MateStudy.controller;


import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.MemberDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.service.AccountService;
import com.MateStudy.MateStudy.service.lecture.TeachLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TeachLectureService teachLectureService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /* 로그인시 데이터베이스의 Member를 스프링 시큐리티가 인가할 CustomedMemberDTO로 전환 고려*/
    @GetMapping("/main")
    public String main(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model) {
        log.info("---------------------------------------------------------------------");
        log.info("System : Request GetMapping on /main");
        log.info(cmDTO.toString());
        log.info(cmDTO.getAuthorities().toString());
        List<LectureDto> lectureDtoList = teachLectureService.getTeachLectureList(cmDTO.getId());

        model.addAttribute("name",cmDTO.getName());
        model.addAttribute("role",cmDTO.getAuthorities().toString());
        model.addAttribute("lectures",lectureDtoList);
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
        return "grade";
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
