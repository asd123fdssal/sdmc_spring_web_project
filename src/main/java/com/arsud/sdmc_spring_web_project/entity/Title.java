package com.arsud.sdmc_spring_web_project.entity;

import lombok.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "COMPANY_id")
    private Company company;

    private byte[] picture;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Transient
    private List<Genre> genreList;

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

    @Transient
    private String series_name;

    @Transient
    private String genre_list_str;

    @Transient
    private String releaseDate_str;
}
