package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.domain.account.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memRepository;

    /** 최준호
     * 회원 도메인과 관련하여 JPA Repositroy를 상속받은 UserRepositroy의 의존성 주입이 잘 되는지 테스트
     */
    @Test
    public void testClass(){
        log.info("Test case : 회원 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = memRepository.getClass().getName();
        Assertions.assertNotNull(proxyName);
        log.info("result : " + proxyName);
    }

    @Test
    public void testStudentSignUp(){
        log.info("INSERT DUMMY MEMBER INTO THE DATABASE");
        final int LENGTH = 4;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        /*최초 실행시*/
        String[] dummyIdInfo = {"2017110000", "2016112000", "2017112001", "2017112002"};
        String[] dummyNameInfo = {"최학생", "김학생", "이학생", "범학생"};
        String[] dummyEmailInfo = {"stdTest@test.com", "stdTest2@test.kr", "stdTest3@test.com", "stdTest4@test.com"};
        String[] dummyPhoneInfo = {"010-1200-0000", "010-1200-0001", "010-1200-0002", "010-1200-0003" };

        for(int i = 0; i<LENGTH; i++){
            Member member = Member.builder().id(dummyIdInfo[i]).pwd(passwordEncoder.encode("1234"))
                    .name(dummyNameInfo[i]).email(dummyEmailInfo[i]).phone(dummyPhoneInfo[i]).build();
            member.addMemberRole(MemberRole.STUDENT);
            memRepository.save(member);
        }
        log.info("DONE");
    }

    @Test
    public void testInstructorSignUp(){
        log.info("INSERT DUMMY MEMBER INTO THE DATABASE");
        final int LENGTH = 4;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String[] dummyIdInfo = {"2017120000", "2017120001", "2017120002", "2017120003"};
        String[] dummyNameInfo = {"김교수", "이교수", "최교수", "김교수"};
        String[] dummyEmailInfo = {"kimProfTest@test.com", "leeProfTest@test.com", "choiProfTest@test.com", "kimProfTest@test.com"};
        String[] dummyPhoneInfo = {"010-1234-5678", "010-1234-5679", "010-1234-5670", "010-1234-5671" };

        for(int i = 0; i<LENGTH; i++){
            Member member = Member.builder().id(dummyIdInfo[i]).pwd(passwordEncoder.encode("1234"))
                    .name(dummyNameInfo[i]).email(dummyEmailInfo[i]).phone(dummyPhoneInfo[i]).build();
            member.addMemberRole(MemberRole.INSTRUCTOR);
            memRepository.save(member);
        }
        log.info("DONE");
    }

    @Test
    public void testAdminSignUp(){
        log.info("INSERT DUMMY MEMBER INTO THE DATABASE");
        final int LENGTH = 4;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        /*
        * 최초 실행시 1번,
        * 현재는 개인정보 노출을 피하고자 제거해놓은 상태
        *
        * */

        /*for(int i = 0; i<LENGTH; i++){
            Member member = Member.builder().id(dummyIdInfo[i]).pwd(passwordEncoder.encode("1234"))
                    .name(dummyNameInfo[i]).email(dummyEmailInfo[i]).phone(dummyPhoneInfo[i]).build();
            member.addMemberRole(MemberRole.ADMIN);
            memRepository.save(member);
        }*/
        log.info("DONE");
    }

    @Test
    public void testFindById(){
        Optional<Member> result = memRepository.findById("2017112095");
        Member member = result.get();
        log.info(member.toString());
    }
}
