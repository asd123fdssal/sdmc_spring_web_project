package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.dto.MemberRegisterDto;
import com.arsud.sdmc_spring_web_project.service.MemberService;
import com.arsud.sdmc_spring_web_project.validator.RegisterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {

    private final MemberService memberService;
    private final RegisterValidator registerValidator;

    @GetMapping
    public String signup(
            Model model
    ){
        model.addAttribute("MemberRegisterDto", new MemberRegisterDto());
        return "/user/signup";
    }

    @InitBinder(value = "/signup")
    public void initBinder(
            WebDataBinder webDataBinder
    ){
        webDataBinder.addValidators(registerValidator);
    }

    @PostMapping
    public String signup(
            @Valid @ModelAttribute("MemberRegisterDto") MemberRegisterDto memberDto,
            BindingResult bindingResult
    ){
        registerValidator.validate(memberDto, bindingResult);

        if(bindingResult.hasErrors()){
            return "/user/signup";
        }
        memberService.signup(
                memberDto.getUsername(),
                memberDto.getPassword(),
                memberDto.getMail(),
                memberDto.getNickname()
        );


        return "/login";
    }

}
