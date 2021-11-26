package com.MateStudy.MateStudy.domain.homework;

import com.MateStudy.MateStudy.domain.common.File;
import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"hwId", "Fid"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "ASSIGN_HW_FILE")
@IdClass(Ahw_FileId.class)
public class Assign_Hw_File {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hwId")
    private Assign_Homework hwId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Fid")
    private File Fid; //우리 패키지의 File 클래스임 조심
}
