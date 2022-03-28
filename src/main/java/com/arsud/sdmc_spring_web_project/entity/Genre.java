package com.arsud.sdmc_spring_web_project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "genre")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean valid;

}
