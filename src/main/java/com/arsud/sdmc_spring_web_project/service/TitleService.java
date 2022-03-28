package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.entity.Company;
import com.arsud.sdmc_spring_web_project.entity.Title;
import com.arsud.sdmc_spring_web_project.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;

    public Title save(
        Company company,
        byte[] picture,
        LocalDateTime releaseDate,
        String korName,
        String orgName,
        String hookCode,
        String etc
    ){
        Title title = Title.builder()
                .company(company)
                .picture(picture)
                .releaseDate(releaseDate)
                .korName(korName)
                .orgName(orgName)
                .hookCode(hookCode)
                .etc(etc)
                .valid(true)
                .build();

        return titleRepository.save(title);
    }

    public List<Title> findAll(){ return titleRepository.findAllByValid(true); }
}
