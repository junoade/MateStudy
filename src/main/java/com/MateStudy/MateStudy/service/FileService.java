package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.common.File;
import com.MateStudy.MateStudy.dto.FileDto;
import com.MateStudy.MateStudy.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileDto fileDto) {
        /* 근데 기본적으로 INC 로 생성되게 됨*/
        if (fileDto.getId() == null) {
            return fileRepository.save(fileDto.toEntityAuto()).getId();
        } else {
            return fileRepository.save(fileDto.toEntity()).getId();
        }
    }

    @Transactional
    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .originFilename(file.getOriginFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }
}
