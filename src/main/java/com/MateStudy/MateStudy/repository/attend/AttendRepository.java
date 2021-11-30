package com.MateStudy.MateStudy.repository.attend;

import com.MateStudy.MateStudy.domain.attendance.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttendRepository extends JpaRepository<Attend, Long> {

    @Query(value="SELECT A FROM Attend A WHERE A.lecCode = :lecCode AND A.subCode = :subCode")
    List<Attend> getEveryAttend(@Param("lecCode") String lecCode, @Param("subCode") Long subCode);


    @Query(value="SELECT DISTINCT A FROM Attend A WHERE A.stId =:stId AND A.instId = :instId AND A.lecCode =:lecCode AND A.subCode = :subCode")
    List<Attend> getMyAttendRecord(@Param("stId") String stId, @Param("instId") String instId, @Param("lecCode") String lecCode, @Param("subCode") Long subCode);

    @Query(value="SELECT DISTINCT A FROM Attend A WHERE A.stId =:stId AND A.instId = :instId AND A.lecCode =:lecCode AND A.subCode = :subCode AND A.week = :week")
    Optional<Attend> getWeekAttend(@Param("stId") String stId, @Param("instId") String instId, @Param("lecCode") String lecCode, @Param("subCode") Long subCode);

}
