package com.MateStudy.MateStudy.domain.question;

import com.MateStudy.MateStudy.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"qno"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "REPLY")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // hwId를 추가하면 따로 Id 등록안해서 자동으로 생성하게끔
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qno")
    private Question qno;

    @Column(name="title")
    private String title;

    @Column(name="content", nullable = false)
    private String content;
}
