package com.arsud.sdmc_spring_web_project.validator;

import com.arsud.sdmc_spring_web_project.entity.Title;
import com.arsud.sdmc_spring_web_project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class TitleValidator implements Validator {

    private final CompanyService companyService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Title.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Title title = (Title) target;

        if(companyService.findByKorName(title.getCompany_name()) == null){
            errors.rejectValue("company", "company_not_found", "존재하지 않는 회사입니다.");
        }

        if(title.getKorName().isBlank()){
            errors.rejectValue("korName", "kor_blank", "게임명을 입력해주세요");
        }

        if(title.getReleaseDate_str().isBlank()){
            errors.rejectValue("releaseDate_str", "date_blank", "발매일을 입력해주세요");
        } else if(!Pattern.matches("^[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]$", title.getReleaseDate_str())){
            errors.rejectValue("releaseDate_str", "date_pattern", "발매일 형식이 올바르지 않습니다.");
        }
    }
}
