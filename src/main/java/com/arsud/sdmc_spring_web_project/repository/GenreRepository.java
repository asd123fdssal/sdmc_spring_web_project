package com.arsud.sdmc_spring_web_project.repository;

import com.arsud.sdmc_spring_web_project.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
    List<Genre> findAllByValid(Boolean valid);
}
