package com.arsud.sdmc_spring_web_project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "series")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Series extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kor_name")
    private String korName;

    @Column(name = "org_name")
    private String orgName;

    private String nickname;

    private Boolean valid;
}
