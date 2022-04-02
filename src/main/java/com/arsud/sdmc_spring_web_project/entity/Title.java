package com.arsud.sdmc_spring_web_project.entity;

import com.arsud.sdmc_spring_web_project.utils.ImageUtilty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
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
    private Date releaseDate;

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

    public Title(Long id, Company company, byte[] picture, Date releaseDate, List<Genre> genreList, String korName, String orgName, String hookCode, String etc, Boolean valid, String company_name, String encodeImage, String series_name, String genre_list_str, String releaseDate_str) {
        this.id = id;
        this.company = company;
        this.picture = picture;
        this.releaseDate = releaseDate;
        this.genreList = genreList;
        this.korName = korName;
        this.orgName = orgName;
        this.hookCode = hookCode;
        this.etc = etc;
        this.valid = valid;
        this.company_name = company_name;
        this.encodeImage = encodeImage;
        this.series_name = series_name;
        this.genre_list_str = genre_list_str;
        this.releaseDate_str = releaseDate_str;
    }

    public Title() {
    }

    public void convertImage(){
        encodeImage = ImageUtilty.makeBase64Image(picture);
    }

    public void makeGenreListStr(){
        genre_list_str = Arrays.toString(genreList.toArray()).replace("[","").
                replace("]","").replace(" ", "");
    }
}
