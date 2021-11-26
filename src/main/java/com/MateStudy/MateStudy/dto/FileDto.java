package com.MateStudy.MateStudy.dto;

import com.MateStudy.MateStudy.domain.common.File;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String originFilename;
    private String filename;
    private String filePath;

    public File toEntity() {
        return File.builder()
                .id(id)
                .originFilename(originFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
    }

    @Builder
    public FileDto(Long id, String originFilename, String filename, String filePath) {
        this.id = id;
        this.originFilename = originFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public File toEntityAuto(){
        return File.builder()
                .originFilename(originFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
    }

    @Builder
    public FileDto(String originFilename, String filename, String filePath) {
        this.originFilename = originFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
