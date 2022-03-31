package com.arsud.sdmc_spring_web_project.validator;

import com.arsud.sdmc_spring_web_project.entity.Characters;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CharactersValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Characters.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Characters characters = (Characters) target;

        if(characters.getKorName().isBlank()){
            errors.rejectValue("korName", "kor_blank", "캐릭터명을 입력해주세요.");
        }
    }
}
