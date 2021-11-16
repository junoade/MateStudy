package com.MateStudy.MateStudy.controller;


import com.MateStudy.MateStudy.domain.account.UserRequest;
import com.MateStudy.MateStudy.service.AccountService;
import com.MateStudy.MateStudy.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // TODO 추후 로그인 세션 정보 활용하게 되면 간단하게 해당 부분 이용하기
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }


    // TODO 추후 로그인 세션정보 활용하기
    @GetMapping("/afterLogin")
    public String afterLogin(@RequestParam(value = "userName") String param, Model model) {
        /*회원정보 담아서 main 페이지로 이동*/
        model.addAttribute("userName", param);
        return "main";
    }
}
