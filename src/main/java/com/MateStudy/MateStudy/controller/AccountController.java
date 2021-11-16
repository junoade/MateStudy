package com.MateStudy.MateStudy.controller;


import com.MateStudy.MateStudy.domain.account.UserRequest;
import com.MateStudy.MateStudy.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/modify")
    public String modify(UserRequest request) throws Exception {
        accountService.modify(request.getId(), request.getPassword());
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest request, RedirectAttributes rttr) throws Exception {
        log.info("로그인 시도");
        ModelAndView mav = new ModelAndView();
        /* TODO 로그인 세션 정보 연동 하기 */
        if (accountService.login(request.getId(), request.getPassword()).equals("Success")) {
            log.info("success");

            /* 로그인 회원 정보 main 페이지로 반환  */
            String username = accountService.getName(request.getId());
            /* TODO 차후 로그인 세션 정보 만들기 */
            rttr.addAttribute("userName", username);

            return "redirect:/afterLogin";
        }
        log.info("failed");
        //return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return "redirect:/";

        //
    }

    // TODO 로그아웃시 세션 만료 명시하기
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
