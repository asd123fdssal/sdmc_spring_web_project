package com.arsud.sdmc_spring_web_project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "title_has_genre")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitleHasGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE_ID")
    private Long titleId;

    @Column(name = "GENRE_ID")
    private Long genreId;

}
