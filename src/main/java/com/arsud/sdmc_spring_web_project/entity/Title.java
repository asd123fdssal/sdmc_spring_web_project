package com.arsud.sdmc_spring_web_project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "title")
@Setter
@Getter
@Builder
public class Title extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "COMPANY_id")
    private Company company;

    private byte[] picture;

    @Column(name = "release_date")
    private LocalDate releaseDate;


    @Column(name = "kor_name")
    private String korName;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "hook_code")
    private String hookCode;

    private String etc;

    private Boolean valid;

    @Transient
    private String company_name;

    @Transient
    private String encodeImage;
}
