package com.MateStudy.MateStudy.domain.question;

import com.MateStudy.MateStudy.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "qno")
@Table(name = "REPLY")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Ano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qno")
    private Question qno;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;
}
