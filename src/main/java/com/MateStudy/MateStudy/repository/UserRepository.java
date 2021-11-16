package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.account.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/* JPA를 통해 관리하는 account 도메인의 엔티티 객체들을 처리하는 Repository */
public interface UserRepository extends JpaRepository<Member, Long> {
    /* 메소드명 findBy 다음에 컬럼명이 카멜 표기법 형태로 와야함
     * 이러한 방식을 쿼리 메소드 ( Query Method ) 라고 부름
     * @Docs : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
     * */
    Optional<Member> findByName(String userName);

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long id);
}
