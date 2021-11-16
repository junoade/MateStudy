package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    /* 최준호
    * long 타입의 학번정보와 pwd를 받아
    * 기존 학번 정보를 조회하고, pwd를 비교한다음 로그인을 처리하는 로직
    * */
    @Override
    public String login(Long id, String pwd) throws Exception {
        log.info("AccountServiceImpl : "+id+" "+ pwd);
        Optional<Member> userWrapper = userRepository.findById(id);
        if(userWrapper.get().getPwd().equals(pwd)){
            return "Success";
        }
        return "Failed";
    }
    /* 이정욱
    * long 타입의 학번 정보와 pwd를 받아
    * 기존의 학번 정보를 조회하고, pwd를 변경하는 로직
    * */
    @Override
    public void modify(Long id, String pwd) {
        Optional<Member> userWrapper = userRepository.findById(id);
        userWrapper.ifPresent(selectUser ->{
            log.info("id : "+selectUser.getId()+", pwd : "+selectUser.getPwd());

            selectUser.setPwd(pwd);
            userRepository.save(selectUser);

            log.info("pwd : "+selectUser.getPwd());
        });
    }
}
