package com.MateStudy.MateStudy.repository.homework;

import com.MateStudy.MateStudy.domain.homework.Assign_Hw_File;
import com.MateStudy.MateStudy.domain.homework.Submit_Hw_File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShwFileRepository extends JpaRepository<Submit_Hw_File, Long> {
    @Modifying
    @Query(value = "INSERT INTO SUBMIT_HW_FILE(Fid, submitId) VALUES(:fid, :submitId)", nativeQuery = true)
    @Transactional
    void insertHwFile(@Param("fid") Long Fid, @Param("submitId") Long submitId);

    @Query(value = "SELECT h.Fid, submitId FROM SUBMIT_HW_FILE h WHERE h.submitId = :submitId", nativeQuery = true)
    List<Submit_Hw_File> getHwFid(@Param("submitId") Long submitId);
}
