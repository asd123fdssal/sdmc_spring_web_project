package com.arsud.sdmc_spring_web_project.entity;

import lombok.*;

import javax.persistence.*;

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


    public String toString() {
        return name;
    }

}
