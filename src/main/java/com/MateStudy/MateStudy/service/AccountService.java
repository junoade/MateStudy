package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.account.MemberRole;
import com.MateStudy.MateStudy.dto.MemberDto;
import com.MateStudy.MateStudy.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    /* 최준호
     * long 타입의 학번정보와 pwd를 받아
     * 기존 학번 정보를 조회하고, pwd를 비교한다음 로그인을 처리하는 로직
     * */
//    @Override
//    public String login(Long id, String pwd) throws Exception {
//        log.info("AccountServiceImpl : " + id + " " + pwd);
//        Optional<Member> userWrapper = userRepository.findById(id);
//        if (userWrapper.get().getPwd().equals(pwd)) {
//            return "Success";
//        }
//        return "Failed";
//    }

    /* 이정욱
     * long 타입의 학번 정보와 pwd를 받아
     * 기존의 학번 정보를 조회하고, pwd를 변경하는 로직
     * */
//    @Override
//    public void modify(Long id, String pwd) {
//        Optional<Member> userWrapper = userRepository.findById(id);
//        userWrapper.ifPresent(selectUser -> {
//            log.info("id : " + selectUser.getId() + ", pwd : " + selectUser.getPwd());
//
//            selectUser.setPwd(pwd);
//            userRepository.save(selectUser);
//
//            log.info("pwd : " + selectUser.getPwd());
//        });
//    }

    @Transactional
    public void signUp(MemberDto memberDto){
        log.info("signing up...");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPwd(passwordEncoder.encode(memberDto.getPwd()));
        memberRepository.save(memberDto.toEntity());
    }

    public void modify(String id, String pwd){
        log.info("modify pwd...");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(selectUser -> {
            log.info("id : " + selectUser.getId() + ", pwd : " + selectUser.getPwd());

            selectUser.setPwd(passwordEncoder.encode(pwd));
            memberRepository.save(selectUser);

            log.info("pwd : " + selectUser.getPwd());
        });
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findById(id);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(id)) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.STUDENT.getValue()));
        }

        /* TODO User를 상속하는 DTO 클래스 만들어서 반환해주기 */
        return new User(userEntity.getId(), userEntity.getPwd(), authorities);
    }

    /**
     * 최준호
     * long 타입의 학번 정보 받아서
     * 사용자 이름을 반환해줌
     */
//    @Override
//    public String getName(Long id) throws Exception {
//        Optional<Member> userWrapper = userRepository.findById(id);
//        return userWrapper.map(Member::getName).orElse("Unknown");
//    }
}
