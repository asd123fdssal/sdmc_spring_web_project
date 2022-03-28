package com.arsud.sdmc_spring_web_project.validator;

import com.arsud.sdmc_spring_web_project.entity.Series;
import com.arsud.sdmc_spring_web_project.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SeriesValidator implements Validator {

    private final SeriesService seriesService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Series.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Series series = (Series) target;

        if(series.getKorName().isBlank()){
            errors.rejectValue("kor_name", "kor_blank", "시리즈명을 입력해주세요.");
        } else if(seriesService.findByKorName(series.getKorName()) != null){
            errors.rejectValue("kor_name", "duplicate_kor", "이미 존재하는 시리즈명입니다.");
        }
    }
}
