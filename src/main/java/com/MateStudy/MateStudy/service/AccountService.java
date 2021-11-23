package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.account.MemberRole;
import com.MateStudy.MateStudy.dto.MemberDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void signUp(MemberDto memberDto) {
        log.info("signing up...");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPwd(passwordEncoder.encode(memberDto.getPwd()));
        memberRepository.save(memberDto.toEntity());
    }

    public void modify(String id, String pwd) {
        /* TODO 검증 절차가 없는데 */
        log.info("modify pwd...");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(selectUser -> {
            log.info("---------------------------------------------------------------------");
            log.info("System : Request to Modify specific User password");
            log.info("id : " + selectUser.getId() + ", pwd : " + selectUser.getPwd());

            selectUser.setPwd(passwordEncoder.encode(pwd));
            memberRepository.save(selectUser);

            log.info("pwd : " + selectUser.getPwd());
        });
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findById(id);
        Member userEntity = userEntityWrapper.get();
        log.info("---------------------------------------------------------------------");
        log.info("Member : " + userEntity.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();

        /* TODO 1120 - 관리자는 여러 권한을 갖을 필요가 있는가? */
        /* List<GrantedAuthority> authorities = new ArrayList<>();*/
        /* DB에 저장된 회원의 ROLE에 따라 Security에서 인가받을 User의 권한 부여 */
        /*if (userEntity.getRole().getValue().equals((MemberRole.ADMIN.getValue()))) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else if (userEntity.getRole().getValue().equals((MemberRole.STUDENT.getValue()))){
            authorities.add(new SimpleGrantedAuthority(MemberRole.STUDENT.getValue()));
        }else if (userEntity.getRole().getValue().equals((MemberRole.INSTRUCTOR.getValue()))){
            authorities.add(new SimpleGrantedAuthority(MemberRole.INSTRUCTOR.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(MemberRole.VISITOR.getValue()));
        }*/

        /* 실제 DB에서의 회원 엔티티를 Security에서 인가받을 User의 상속체 CustomedMemberDTO로 값 전달 */
        return new CustomedMemberDTO(
                userEntity.getId(),
                userEntity.getPwd(),
                userEntity.getName(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getRole().stream().map(role -> new SimpleGrantedAuthority((role.name()))).collect(Collectors.toSet()) // 현재 회원의 권한 전부 읽어서 반환
        );
    }

    /**
     * 최준호
     * long 타입의 학번 정보 받아서
     * 사용자 이름을 반환해줌
     */
    /*@Override
    public String getName(Long id) throws Exception {
        Optional<Member> userWrapper = userRepository.findById(id);
        return userWrapper.map(Member::getName).orElse("Unknown");
    }*/
    /* 최준호
     * long 타입의 학번정보와 pwd를 받아
     * 기존 학번 정보를 조회하고, pwd를 비교한다음 로그인을 처리하는 로직
     * */
    /*@Override
    public String login(Long id, String pwd) throws Exception {
        log.info("AccountServiceImpl : " + id + " " + pwd);
        Optional<Member> userWrapper = userRepository.findById(id);
        if (userWrapper.get().getPwd().equals(pwd)) {
            return "Success";
        }
        return "Failed";
    }*/

    /* 이정욱
     * long 타입의 학번 정보와 pwd를 받아
     * 기존의 학번 정보를 조회하고, pwd를 변경하는 로직
     * */
    /*@Override
    public void modify(Long id, String pwd) {
        Optional<Member> userWrapper = userRepository.findById(id);
        userWrapper.ifPresent(selectUser -> {
            log.info("id : " + selectUser.getId() + ", pwd : " + selectUser.getPwd());

            selectUser.setPwd(pwd);
            userRepository.save(selectUser);

            log.info("pwd : " + selectUser.getPwd());
        });
    }*/
}
