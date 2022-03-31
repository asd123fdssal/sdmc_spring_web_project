package com.arsud.sdmc_spring_web_project.repository;

import com.arsud.sdmc_spring_web_project.entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Long> {

}
