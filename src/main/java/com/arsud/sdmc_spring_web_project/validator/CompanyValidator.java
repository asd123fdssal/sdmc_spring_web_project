package com.arsud.sdmc_spring_web_project.validator;

import com.arsud.sdmc_spring_web_project.entity.Company;
import com.arsud.sdmc_spring_web_project.entity.Series;
import com.arsud.sdmc_spring_web_project.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CompanyValidator implements Validator {

    private final SeriesService seriesService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Company.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Company company = (Company) target;

        if(company.getKorName().isBlank()){
            errors.rejectValue("kor_name", "kor_blank", "회사명을 입력해주세요.");
        } else if(seriesService.findByKorName(company.getKorName()) != null){
            errors.rejectValue("kor_name", "duplicate_kor", "이미 존재하는 회사명입니다.");
        }
    }
}
