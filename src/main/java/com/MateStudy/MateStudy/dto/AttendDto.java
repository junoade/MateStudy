package com.MateStudy.MateStudy.dto;

import com.MateStudy.MateStudy.domain.attendance.Attend;
import com.MateStudy.MateStudy.domain.homework.Assign_Homework;
import com.MateStudy.MateStudy.domain.homework.Submit_Homework;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class AttendDto {
    private Long attendId;
    private String status;
    private Long week;
    private String stId;
    private String instId;
    private String lecCode;
    private Long subCode;

    public Attend toEntityAuto(){
        return Attend.builder()
                //.attendId(attendId) 자동 생성
                .status(status)
                .week(week)
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .build();
    }

    public AttendDto(String status, Long week, String stId, String instId, String lecCode, Long subCode){
        this.status = status;
        this.week = week;
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
    }

    /* submitId 명시적으로 지정시 */
    public Attend toEntityWithId() {
        return Attend.builder()
                .attendId(attendId)
                .status(status)
                .week(week)
                .stId(stId)
                .instId(instId)
                .lecCode(lecCode)
                .subCode(subCode)
                .build();
    }

    public AttendDto(Long attendId, String status, Long week, String stId, String instId, String lecCode, Long subCode){
        this.attendId = attendId;
        this.status = status;
        this.week = week;
        this.stId = stId;
        this.instId = instId;
        this.lecCode = lecCode;
        this.subCode = subCode;
    }
}
