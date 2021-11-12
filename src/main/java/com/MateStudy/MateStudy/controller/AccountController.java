package com.MateStudy.MateStudy.controller;


import com.MateStudy.MateStudy.domain.account.UserRequest;
import com.MateStudy.MateStudy.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public String login(UserRequest request) throws Exception {
        log.info("로그인 시도");
        if (accountService.login(request.getId(), request.getPassword()).equals("Success")) {
            log.info("success");
            //return new ResponseEntity(HttpStatus.OK);
            return "redirect:/main";
        }
        log.info("failed");
        //return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return "redirect:/";

        //
    }
}
