package com.MateStudy.MateStudy.domain.homework;

import com.MateStudy.MateStudy.domain.common.BaseEntity;
import com.MateStudy.MateStudy.domain.common.File;
import lombok.*;

import javax.persistence.*;

/**
 * 부여된 실습과제 엔티티와 File 엔티티간의 관계
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"submitId", "Fid"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "SUBMIT_HW_FILE")
@IdClass(Shw_FileId.class)
public class Submit_Hw_File {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="submitId")
    private Submit_Homework submitId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Fid")
    private File Fid; //우리 패키지의 File 클래스임 조심

}
