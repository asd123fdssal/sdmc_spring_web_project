package com.arsud.sdmc_spring_web_project.dto;

import com.arsud.sdmc_spring_web_project.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GenreList {
    private Long id;

    private String name;

    private Boolean valid;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public Genre toGenre(){
        return Genre.builder()
                .id(id)
                .name(getName())
                .valid(getValid())
                .build();
    }
}
