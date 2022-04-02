package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.entity.Company;
import com.arsud.sdmc_spring_web_project.entity.Genre;
import com.arsud.sdmc_spring_web_project.entity.Series;
import com.arsud.sdmc_spring_web_project.entity.Title;
import com.arsud.sdmc_spring_web_project.repository.TitleRepository;
import com.arsud.sdmc_spring_web_project.repository.template.TitleTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;
    private final TitleTemplateRepository titleTemplateRepository;

    public Title save(
        Company company,
        Series series,
        byte[] picture,
        Date releaseDate,
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
    public Title findById(Long id) { return titleRepository.findByValidAndId(true, id); }
    public Title findByTitleId(Long title_id) { return titleTemplateRepository.findByTitleId(title_id); }
}
