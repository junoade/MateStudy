package com.MateStudy.MateStudy.service.homework;

import com.MateStudy.MateStudy.repository.FileRepository;
import com.MateStudy.MateStudy.repository.homework.AhwFileRepository;
import com.MateStudy.MateStudy.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class FileServiceTest {

    @Autowired
    FileService fileService;

    @Autowired
    FileRepository fileRepository;

    @Test
    public void testClassFileService() {
        String proxyName = fileService.getClass().getName();
        log.info("result : " + proxyName);
        Assertions.assertNotNull(proxyName);
    }

}
