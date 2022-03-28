package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.entity.Company;
import com.arsud.sdmc_spring_web_project.entity.Series;
import com.arsud.sdmc_spring_web_project.service.CompanyService;
import com.arsud.sdmc_spring_web_project.service.SeriesService;
import com.arsud.sdmc_spring_web_project.validator.CompanyValidator;
import com.arsud.sdmc_spring_web_project.validator.SeriesValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyValidator companyValidator;
    private final CompanyService companyService;

    @InitBinder("/game/company/*")
    public void initBinder(
            WebDataBinder webDataBinder
    ){
        webDataBinder.addValidators(companyValidator);
    }

    @GetMapping("/game/company/new")
    public String new_company(
            Model model
    ) {
        model.addAttribute("Company", new Company());
        return "/game/company/new";
    }

    @PostMapping("/game/company/new")
    public String new_company(
            Model model,
            @Valid @ModelAttribute("Company") Company company,
            BindingResult bindingResult
    ){
        companyValidator.validate(company, bindingResult);

        if(!bindingResult.hasErrors()){
            companyService.save(
                    company.getKorName(),
                    company.getOrgName()
            );
            company.setKorName("");
            company.setOrgName("");
            model.addAttribute("success", true);
        }else{
            model.addAttribute("success", false);
        }
        return "/game/company/new";
    }

    @GetMapping("/game/company/list")
    public String company_list(
            Model model
    ){
        model.addAttribute("company_list", companyService.findAll());
        return "/game/company/list";
    }
}
