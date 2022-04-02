package com.arsud.sdmc_spring_web_project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member_data")
public class MemberData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "MEMBER_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "character_id")
    private Characters characters;

    @OneToOne
    @JoinColumn(name = "character_title_id")
    private Title title;

    @Column(name = "etc", length = 500)
    private String etc;

    @Column(name = "progress", length = 10)
    private String progress;

    @Column(name = "valid", nullable = false)
    private Boolean valid;
}