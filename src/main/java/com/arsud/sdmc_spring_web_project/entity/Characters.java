package com.arsud.sdmc_spring_web_project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "characters")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Characters extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "title_id")
    private Title title;

    private byte[] picture;

    @Column(name = "kor_name")
    private String korName;

    @Column(name = "org_name")
    private String orgName;

    private String strategy;

    private Boolean valid;

    @Transient
    private String encodeImage;
}
