package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.account.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JPARepository
public interface UserRepository extends CrudRepository<Member, Long> {
    /* 메소드명 findBy 다음에 컬럼명이 카멜 표기법 형태로 와야함*/
    Optional<Member> findByName(String userName);
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long id);
}
