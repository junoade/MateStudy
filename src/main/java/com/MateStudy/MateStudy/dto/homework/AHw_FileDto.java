package com.MateStudy.MateStudy.dto.homework;

import com.MateStudy.MateStudy.domain.common.File;
import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.homework.Assign_Hw_File;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Assigned Homework DTO
 */
@ToString
@Data
@NoArgsConstructor
public class AHw_FileDto {
    private Assign_Homework hwId;
    private File fid;

    public Assign_Hw_File toEntity(){
        return Assign_Hw_File.builder().hwId(hwId).Fid(fid).build();
    }

    @Builder
    public AHw_FileDto(Assign_Homework hwId, File fid){
        this.hwId = hwId;
        this.fid = fid;
    }
    @Builder
    public AHw_FileDto(Long hwId, Long fid) {
        this.hwId.setHwId(hwId);
        this.fid.setId(fid);
    }
}
