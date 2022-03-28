package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.entity.Company;
import com.arsud.sdmc_spring_web_project.entity.Series;
import com.arsud.sdmc_spring_web_project.repository.CompanyRepository;
import com.arsud.sdmc_spring_web_project.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company save(
        String korName,
        String orgName
    ){
        Company company = Company.builder()
                .korName(korName)
                .orgName(orgName)
                .valid(true)
                .build();

        return companyRepository.save(company);
    }

    public Company findByKorName(String korName){
        return companyRepository.findByKorName(korName);
    }

    public List<Company> findAll() { return companyRepository.findAllByValid(true); }
}
