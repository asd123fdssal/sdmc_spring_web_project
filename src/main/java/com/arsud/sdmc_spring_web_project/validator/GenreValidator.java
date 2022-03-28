package com.arsud.sdmc_spring_web_project.validator;

import com.arsud.sdmc_spring_web_project.entity.Genre;
import com.arsud.sdmc_spring_web_project.service.GenreService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class GenreValidator implements Validator {

    private final GenreService genreService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Genre.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Genre genre = (Genre) target;

        if(genre.getName().isBlank()){
            errors.rejectValue("name", "name_blank", "장르명을 입력해주세요.");
        } else if (genreService.findByName(genre.getName()) != null){
            errors.rejectValue("name", "duplicate_name", "동일한 장르명이 존재합니다.");
        }
    }
}
