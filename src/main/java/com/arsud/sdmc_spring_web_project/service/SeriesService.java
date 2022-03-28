package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.entity.Series;
import com.arsud.sdmc_spring_web_project.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public Series save(
        String korName,
        String orgName,
        String nickName
    ){
        Series series = Series.builder()
                .korName(korName)
                .orgName(orgName)
                .nickname(nickName)
                .valid(true)
                .build();

        return seriesRepository.save(series);
    }

    public Series findByKorName(String korName){
        return seriesRepository.findByKorName(korName);
    }

    public List<Series> findAll() { return seriesRepository.findAllByValid(true); }
}
