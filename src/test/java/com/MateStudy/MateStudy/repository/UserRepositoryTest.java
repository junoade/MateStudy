package com.MateStudy.MateStudy.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    /** 최준호
     * 회원 도메인과 관련하여 JPA Repositroy를 상속받은 UserRepositroy의 의존성 주입이 잘 되는지 테스트
     */
    @Test
    public void testClass(){
        log.info("Test case : 회원 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = userRepository.getClass().getName();
        Assertions.assertNotNull(proxyName);
        log.info("result : " + proxyName);
    }
}
