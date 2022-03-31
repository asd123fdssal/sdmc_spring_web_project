package com.arsud.sdmc_spring_web_project.repository;

import com.arsud.sdmc_spring_web_project.dto.GenreList;
import com.arsud.sdmc_spring_web_project.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
    List<Genre> findAllByValid(Boolean valid);
    @Query(value = "select new com.arsud.sdmc_spring_web_project.dto.GenreList(g.id, g.name, g.valid, g.created_at, g.updated_at) from Genre g, TitleHasGenre thg where thg.genreId = g.id and thg.titleId = :titleId")
    List<GenreList> findByTitleId(@Param("titleId") Long titleId);
}
