package com.MateStudy.MateStudy.controller;


import com.MateStudy.MateStudy.dto.MemberDto;
import com.MateStudy.MateStudy.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // TODO 추후 로그인 세션 정보 활용하게 되면 간단하게 해당 부분 이용하기
    @GetMapping("/main")
    public String main() {
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
        return "redirect:main";
    }
    @GetMapping("/member/denied")
    public String denied(){
        return "denied";
    }

}
