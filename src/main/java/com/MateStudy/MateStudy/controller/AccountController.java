package com.MateStudy.MateStudy.controller;


import com.MateStudy.MateStudy.domain.account.UserRequest;
import com.MateStudy.MateStudy.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity login( UserRequest request) throws Exception {
        log.info("로그인 시도");
        if(accountService.login(request.getId(), request.getPassword()).equals("Success")){
            log.info("success");
            return new ResponseEntity(HttpStatus.OK);
        }
        log.info("failed");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
