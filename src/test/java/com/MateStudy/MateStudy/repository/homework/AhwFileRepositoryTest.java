package com.MateStudy.MateStudy.repository.homework;

import com.MateStudy.MateStudy.domain.homework.Assign_Hw_File;
import com.MateStudy.MateStudy.dto.homework.AHw_FileDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * assigned homework 에서 file 등록 테스트
 */
@Slf4j
@SpringBootTest
public class AhwFileRepositoryTest {

    @Autowired
    AhwFileRepository ahwFileRepository;

    @Test
    public void testClass() {
        log.info("Test case : 강좌 도메인 관련 JPA Repository 의존성 주입 테스트");
        String proxyName = ahwFileRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    /* 특정 실습과제의 hwId를 기준으로 연관된 Assgin_HW_File 리턴 */
    @Test
    @Transactional
    public void TestGetHwFid() {
        boolean testStatus = false;
        Long hwId = 16L;
        Long Fid = 1L;
        try {
            List<Assign_Hw_File> files = ahwFileRepository.getHwFid(hwId);
            List<AHw_FileDto> result = new ArrayList<>();
            for (Assign_Hw_File file : files) {
                AHw_FileDto afDto = AHw_FileDto.builder()
                        .fid(file.getFid())
                        .hwId(file.getHwId())
                        .build();
                result.add(afDto);
            }
            log.info(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assertions.assertTrue(testStatus);
        }

    }

}
