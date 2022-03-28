package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.entity.Company;
import com.arsud.sdmc_spring_web_project.entity.Title;
import com.arsud.sdmc_spring_web_project.service.CompanyService;
import com.arsud.sdmc_spring_web_project.service.TitleService;
import com.arsud.sdmc_spring_web_project.validator.TitleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;
    private final CompanyService companyService;
    private final TitleValidator titleValidator;

    @GetMapping("/game/title/list")
    public String game(
            Model model
    ){
        List<Title> tList = titleService.findAll();
        model.addAttribute("title_list", titleService.findAll());
        return "/game/title/list";
    }

    @GetMapping("/game/title/new")
    public String game_new(
            Model model
    ){
        model.addAttribute("Title", new Title());
        model.addAttribute("Company", companyService.findAll());
        return "/game/title/new";
    }


    @PostMapping("/game/title/new")
    public String game_new(
            @Valid @ModelAttribute("Title") Title title,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file
            ){
        titleValidator.validate(title, bindingResult);

        title.setCompany(companyService.findByKorName(title.getCompany_name()));
        byte[] image = null;

        if(bindingResult.hasErrors()){
            return "redirect:/";
        }

        if(!file.isEmpty()){
            try {
                image = file.getInputStream().readAllBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        titleService.save(
            title.getCompany(),
                image,
                title.getReleaseDate(),
                title.getKorName(),
                title.getOrgName(),
                title.getHookCode(),
                title.getEtc()
        );

        return "redirect:/game/title/list";
    }

}
