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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ASSIGN_HW_FILE 테이블
 * ASSIGN_HOMEWORK 와 FILE간의 M:N 관계 해소
 */
@Slf4j
@Service
public class AhwFileService {

    @Autowired
    AhwFileRepository assignedRepository;

    @Autowired
    FileService fileService;

    @Autowired
    FileRepository fileRepository;

    /**
     * 현재 hwId 정보, FileDto를 바탕으로, DB에 새로운 File 만들고 맵핑시켜주기
     * @param hwId    첨부된 파일과 mapping될 실습과제의 hwId
     * @param fileDto 첨부된 파일의 정보를 담은 Dto 객체
     */
    @Transactional
    public void saveAssignedFile(Long hwId, FileDto fileDto) {
        /* 1) 먼저 File 테이블에 새로운 File을 만든다, 그후 해당 데이터의 fid를 반환해온다.  */
        Long fid = fileService.saveFile(fileDto);
        /* 2) File 테이블에서 만든 엔티티의 id를 가져와서, ASSIGN_HW_FILE에 등록한다 */
        try {
            assignedRepository.insertHwFile(fid, hwId);
        } catch (Exception e) {
            log.info("failed to save File");
            e.printStackTrace();
        }
        /* 3) 종료*/
    }

    /**
     * 현재 실습과제정보, 올리고자하는 파일정보 DB에 새로운 File 만들고 맵핑시켜주기
     * @param ahDto   View 로부터 받은 실습과제 정보
     * @param fileDto View 로부터 받은 파일 정보
     */
    @Transactional
    public void saveAssignedFile(Assign_HomeworkDto ahDto, FileDto fileDto) {
        /* 1) 먼저 File 테이블에 새로운 File을 만든다 */
        Long fid = fileService.saveFile(fileDto);
        /* 2) File 테이블에서 만든 엔티티의 id를 가져와서, ASSIGN_HW_FILE에 등록한다 */
        try {
            assignedRepository.insertHwFile(fid, ahDto.getHwId());
        } catch (Exception e) {
            log.info("failed to save File");
            e.printStackTrace();
        }
        /* 3) 종료 */
    }
    /**
     * 현재 hwId를 바탕으로 모든 Assign_HW_File 데이터를 가져온 다음
     * FileDTO 가져오기
     */
    @Transactional
    public List<FileDto> getFileDtoByHwId(Long hwId){
        List<Assign_Hw_File> ahw_files = assignedRepository.getHwFid(hwId);
        List<File> fileList = new ArrayList<>();
        for(Assign_Hw_File ahw_file : ahw_files){
            fileList.add(fileRepository.getById(ahw_file.getFid().getId()));
        }

        List<FileDto> result = new ArrayList<>();
        for(File file : fileList){
            FileDto fDto = FileDto.builder()
                    .id(file.getId())
                    .filePath(file.getFilePath())
                    .filename(file.getFilename())
                    .originFilename(file.getOriginFilename())
                    .build();
            result.add(fDto);
        }
        return result;
    }


/**
 * 현재 hwId를 바탕으로 모든 ASSIGN_HW_FILE 데이터 가져오기
 * TODO Dto로 계속 전달한건지, hwId로 전달할건지 논의할 것
 * @see com.MateStudy.MateStudy.domain.common.File
 * @see com.MateStudy.MateStudy.domain.homework.Assign_Homework
 */
/*
    @Transactional
    public List<AHw_FileDto> getAllSubmitHw(Assign_HomeworkDto ahDto) {
        List<Assign_Hw_File> list = assignedRepository.getAllSubmitFiles(ahDto.getHwId());
        List<AHw_FileDto> result = new ArrayList<>();
        for (Assign_Hw_File af : list) {
            AHw_FileDto afDto = AHw_FileDto.builder()
                    .hwId(af.getHwId())
                    .fid(af.getFid())
                    .build();
            result.add(afDto);
        }
        return result;
    }
    @Transactional
    public List<AHw_FileDto> getAllSubmitHw(Long hwId) {
        List<Assign_Hw_File> list = assignedRepository.getAllSubmitFiles(hwId);
        List<AHw_FileDto> result = new ArrayList<>();
        for (Assign_Hw_File af : list) {
            AHw_FileDto afDto = AHw_FileDto.builder()
                    .hwId(af.getHwId())
                    .fid(af.getFid())
                    .build();
            result.add(afDto);
        }
        return result;
    }

    */
/**
 * 현재 hwId를 바탕으로 모든 Assign File의 실제 File 정보를 가져옴
 *//*

    @Transactional
    public List<FileDto> getAllFiles(Assign_HomeworkDto ahDto) {
        List<File> list = assignedRepository.getAllFiles(ahDto.getHwId());
        List<FileDto> result = new ArrayList<>();
        for (File f : list) {
            FileDto fDto = FileDto.builder().id(f.getId())
                    .filePath(f.getFilePath())
                    .filename(f.getFilename())
                    .originFilename(f.getOriginFilename())
                    .build();
            result.add(fDto);
        }
        return result;
    }

    */
/**
 * 특정 hwId와 특정 fId를 입력받아서 SubmitHw 반환
 *//*

    @Transactional
    public AHw_FileDto getOneSubmitFile(Long hwId, Long fid) {
        Optional<Assign_Hw_File> ahf = assignedRepository.getOneSubmitFile(hwId, fid);
        return ahf.map(assign_hw_file -> AHw_FileDto.builder()
                .hwId(assign_hw_file.getHwId())
                .fid(assign_hw_file.getFid())
                .build()).orElse(null);
    }
*/

    /**
     * 특정 hwId와 특정 fId를 입력받아서 FileDto 반환
     */
    /*@Transactional
    public FileDto getOneFile(Long hwId, Long fid) {
        Optional<File> file = assignedRepository.getOneFile(hwId, fid);
        return file.map(temp -> FileDto.builder()
                .id(temp.getId())
                .filePath(temp.getFilePath())
                .filename(temp.getFilename())
                .originFilename(temp.getOriginFilename())
                .build()).orElse(null);
    }*/
}
