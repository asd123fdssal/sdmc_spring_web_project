package com.arsud.sdmc_spring_web_project.repository;

import com.arsud.sdmc_spring_web_project.entity.Characters;
import com.arsud.sdmc_spring_web_project.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Long> {

    List<Characters> findAllByTitle(Title title);
    Characters findAllById(Long id);
}
