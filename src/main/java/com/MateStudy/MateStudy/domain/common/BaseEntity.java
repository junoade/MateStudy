package com.MateStudy.MateStudy.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 회원 정보, 실습 과제, 파일 등 전반에 걸쳐 생성된 날짜와 수정 날짜에 관한 기본 엔티티
 */
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name="regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "modDate")
    private LocalDateTime modDate;
}