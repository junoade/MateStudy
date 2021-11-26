package com.MateStudy.MateStudy.service.homework;

import com.MateStudy.MateStudy.domain.common.File;
import com.MateStudy.MateStudy.domain.homework.Assign_Hw_File;
import com.MateStudy.MateStudy.dto.FileDto;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.dto.homework.AHw_FileDto;
import com.MateStudy.MateStudy.repository.FileRepository;
import com.MateStudy.MateStudy.repository.homework.AhwFileRepository;
import com.MateStudy.MateStudy.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class AhwFileServiceTest {

    @Autowired
    AhwFileRepository assignedRepository;

    @Autowired
    AhwFileService ahwFileService;

    @Autowired
    FileService fileService;

    @Autowired
    FileRepository fileRepository;


    /* 의존성 주입 테스트*/
    @Test
    public void testClassARptry() {
        String proxyName = assignedRepository.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    @Test
    public void testClassAhwFileSerivce() {
        String proxyName = ahwFileService.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    @Test
    public void testClassFileService() {
        String proxyName = fileService.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

    /* 과제 제출 시 파일 등록한다고 한다 할 때 테스트 케이스 */
    @Test
    @Transactional
    public void TestSaveAssignedFile() {
        boolean testStatus = false;
        try {
            /* 실습과제 Dto 가정 */
            Long hwId = 16L; // 실제로 있는 교수자가 등록한 hwId를 입력할 것, 바로 새로운 과제 추가는 연관관계 때문에 불가능

            /*
            Long hwId = 17L;
            String instId = "2017120002";
            String lecCode = "CSE4036";
            Long subCode = 1L;
            String title = "TEST FILE UPLOAD";
            String content = "TESTING TESTING";
            LocalDateTime dueDate = LocalDateTime.of(2021, 12, 25, 0, 0, 0);
            Boolean isDone = LocalDateTime.now().isAfter(dueDate);
            Assign_HomeworkDto ahDto = new Assign_HomeworkDto(hwId, instId, lecCode, subCode, title, content, dueDate, isDone);
            */

            /* 실습과제에서 File 등록 요청 가정*/
            Long fid = 27L;
            String filePath = "/muyaho";
            String fileName = "Homework3.txt";
            String originFileName = "Homework3.txt";
            FileDto fileDto = new FileDto(fid, originFileName, fileName, filePath);

            /* 실습과제로 부터 File 생성 */
            ahwFileService.saveAssignedFile(hwId, fileDto);
            log.info(" 실습과제 첨부파일 등록 완료");
            testStatus = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assertions.assertTrue(testStatus);
            //TestTransaction.flagForCommit();
        }
    }

    @Test
    @Transactional
    public void TestGetFilesDtoByHwId() {
        boolean testStatus = false;
        Long hwId = 16L;
        try {
            List<FileDto> fileDtos = ahwFileService.getFileDtoByHwId(hwId);
            for (FileDto file : fileDtos) {
                log.info(file.toString());
            }
            testStatus = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            Assertions.assertTrue(testStatus);
        }

    }


}
