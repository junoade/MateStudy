package com.MateStudy.MateStudy.repository.homework;

import com.MateStudy.MateStudy.domain.common.File;
import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.homework.Assign_Hw_File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 파일 데이터 삽입 후 실습과제 연관 테이블로 삽입,
 * 특정 실습과제의 hwId를 기준으로 연관된 Assign_Hw_File 반환 ( 그 후 서비스에서 해당 쿼리문을 이용해서 File 정보 추출)
 */
public interface AhwFileRepository extends JpaRepository<Assign_Hw_File, Long> {

    /* 데이터 삽입 시, File 테이블에 먼저 삽입 후, ASSIGN_HW_FILE에 맵핑 */
    @Modifying
    @Query(value = "INSERT INTO ASSIGN_HW_FILE(Fid, hwId) VALUES(:fid, :hwId)", nativeQuery = true)
    @Transactional
    void insertHwFile(@Param("fid") Long Fid, @Param("hwId") Long hwId);

    /* 특정 실습과제의 hwId를 기준으로 연관된 Assgin_HW_File 리턴*/
    @Query(value = "SELECT h.Fid, hwId FROM ASSIGN_HW_FILE h WHERE h.hwId = :hwId", nativeQuery = true)
    List<Assign_Hw_File> getHwFid(@Param("hwId") Long hwId);

}
