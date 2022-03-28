package com.arsud.sdmc_spring_web_project.validator;

import com.arsud.sdmc_spring_web_project.dto.MemberRegisterDto;
import com.arsud.sdmc_spring_web_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class RegisterValidator implements Validator {

    private final MemberService memberService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberRegisterDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberRegisterDto memberDto = (MemberRegisterDto) target;

        if (memberDto.getUsername().isBlank()){
            errors.rejectValue("username", "id_blank", "아이디를 입력해주세요.");
        } else if (memberDto.getUsername().length() < 4){
            errors.rejectValue("username", "id_length", "아이디를 4글자 이상 입력해주세요.");
        } else if (memberService.findByUsername(memberDto.getUsername()) != null){
            errors.rejectValue("username", "duplicate_id", "이미 존재하는 아이디 입니다.");
        }

        if (memberDto.getPassword().isBlank()){
            errors.rejectValue("password", "pw_blank", "비밀번호를 입력해주세요.");
        } else if (memberDto.getPassword().length() < 4){
            errors.rejectValue("password", "pw_length", "비밀번호를 4글자 이상 입력해주세요.");
        } else if (memberDto.getPassword_confirm().isBlank()){
            errors.rejectValue("password", "pw_confirm_blank", "비밀번호를 재입력해주세요.");
        } else if (!memberDto.getPassword_confirm().equals(memberDto.getPassword())){
            errors.rejectValue("password", "pw_not_matched", "비밀번호가 일치하지 않습니다.");
        }

        String regex = "[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        if (memberDto.getMail().isBlank()){
            errors.rejectValue("mail", "mail_blank", "메일 주소를 입력해주세요.");
        } else if (!Pattern.matches(regex, memberDto.getMail())){
            errors.rejectValue("mail", "mail_pattern_error", "메일 주소 형식이 일치하지 않습니다.");
        }

        if (memberDto.getNickname().isBlank()){
            errors.rejectValue("nickname", "nickname_blank", "닉네임을 입력해주세요.");
        } else if (memberDto.getNickname().length() < 3){
            errors.rejectValue("nickname", "nickname_length", "닉네임을 3글자 이상 입력해주세요.");
        } else if(memberService.findByNickname(memberDto.getNickname()) != null){
            errors.rejectValue("nickname", "duplication_nickname", "이미 존재하는 닉네임 입니다.");
        }
    }
}
