package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.account.Member;
import com.MateStudy.MateStudy.dto.MemberDto;
import com.MateStudy.MateStudy.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public String getMemberName(String stId){
        Optional<Member> member = memberRepository.findById(stId);
        return member.map(value -> member.get().getName()).orElse("");
    }
}
