package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.account.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/* JPA를 통해 관리하는 account 도메인의 엔티티 객체들을 처리하는 Repository */
public interface MemberRepository extends JpaRepository<Member, String> {
    /* 메소드명 findBy 다음에 컬럼명이 카멜 표기법 형태로 와야함
     * 이러한 방식을 쿼리 메소드 ( Query Method ) 라고 부름
     * @Docs : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
     * */
    Optional<Member> findByName(String userName);

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(String id);

    /*  복잡한 쿼리문의 경우 @Query 어노테이션을 활용할 수 있다.
     *  SQL의 where구문에 대해 파라미터 바인딩 기능 제공한다.
     *  Native SQL 처리를 할 수 있다. JPA 를 사용하는 장점은 잃을 수 있으나, 복잡한 JOIN 구문을 처리하기 위해 사용된다.
     * */

    /* 해당 강좌에 속한 학생들의 정보 반환 */
    @Query(value="SELECT M FROM Member M WHERE M.id IN (SELECT tl.stId FROM Taking_Lecture tl WHERE tl.instId=:instId AND tl.lecCode=:lecCode AND tl.subCode=:subCode)")
    List<Member> getClassStudents(@Param("instId") String instId, @Param("lecCode") String lecCode, @Param("subCode") Long subCode);

    /* 교수자에게 속한 모든 학생들 정보 반환*/
    @Query(value="SELECT M FROM Member M WHERE M.id IN (SELECT tl.stId FROM Taking_Lecture tl WHERE tl.instId=:instId)")
    List<Member> getMyStudents(@Param("instId") String instId);


}
