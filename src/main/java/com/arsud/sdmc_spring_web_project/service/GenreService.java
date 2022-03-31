package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.dto.GenreList;
import com.arsud.sdmc_spring_web_project.entity.Genre;
import com.arsud.sdmc_spring_web_project.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre save(String name){
        return genreRepository.save(
                Genre.builder()
                .name(name)
                .valid(true)
                .build());
    }

    public Genre findByName(String name){
        return genreRepository.findByName(name);
    }

    public List<Genre> findAll() { return genreRepository.findAllByValid(true); }

    public List<GenreList> findGenreListByTitleId(Long titleId) { return genreRepository.findByTitleId(titleId); }
}
