package com.MateStudy.MateStudy.service.homework;

import com.MateStudy.MateStudy.domain.common.File;
import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.homework.Assign_Hw_File;
import com.MateStudy.MateStudy.domain.homework.Submit_Hw_File;
import com.MateStudy.MateStudy.dto.FileDto;
import com.MateStudy.MateStudy.repository.FileRepository;
import com.MateStudy.MateStudy.repository.homework.ShwFileRepository;
import com.MateStudy.MateStudy.repository.lecture.Assign_HomeworkRepository;
import com.MateStudy.MateStudy.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ShwFileService {
    @Autowired
    ShwFileRepository shwFileRepository;

    @Autowired
    FileService fileService;

    @Autowired
    FileRepository fileRepository;

    @Transactional
    public void saveSubmittedFile(Long submitId, FileDto fileDto){
        Long fid = fileService.saveFile(fileDto);
        try{
            shwFileRepository.insertHwFile(fid, submitId);
        }catch(Exception e){
            log.info("failed to save File");
            e.printStackTrace();
        }
    }
    @Transactional
    public List<FileDto> getFileDtoBySubmitId(Long submitId){
        List<Submit_Hw_File> shw_files = shwFileRepository.getHwFid(submitId);
        log.info("shw_files size :"+shw_files.size()+"");
        List<File> fileList = new ArrayList<>();
        for(Submit_Hw_File shw_file : shw_files){
            fileList.add(fileRepository.getById(shw_file.getFid().getId()));
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
}
