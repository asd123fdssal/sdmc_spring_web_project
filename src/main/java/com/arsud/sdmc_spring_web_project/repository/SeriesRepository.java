package com.arsud.sdmc_spring_web_project.repository;

import com.arsud.sdmc_spring_web_project.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Series findByKorName(String kor_name);
    List<Series> findAllByValid(Boolean valid);
}
