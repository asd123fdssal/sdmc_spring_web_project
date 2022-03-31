package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.entity.Genre;
import com.arsud.sdmc_spring_web_project.entity.Title;
import com.arsud.sdmc_spring_web_project.entity.TitleHasGenre;
import com.arsud.sdmc_spring_web_project.repository.TitleHasGenreRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleHasGenreService {

    private final TitleHasGenreRepository titleHasGenreRepository;

    public void save(
            Long title_id,
            List<Genre> genreList
    ){
        for (Genre g : genreList) {
            TitleHasGenre thg = TitleHasGenre.builder()
                    .titleId(title_id)
                    .genreId(g.getId())
                    .build();
            titleHasGenreRepository.save(thg);
        }
    }
}
