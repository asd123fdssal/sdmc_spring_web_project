package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.entity.Genre;
import com.arsud.sdmc_spring_web_project.entity.Series;
import com.arsud.sdmc_spring_web_project.service.GenreService;
import com.arsud.sdmc_spring_web_project.service.SeriesService;
import com.arsud.sdmc_spring_web_project.validator.GenreValidator;
import com.arsud.sdmc_spring_web_project.validator.SeriesValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesValidator seriesValidator;
    private final SeriesService seriesService;

    @InitBinder("/game/series/*")
    public void initBinder(
            WebDataBinder webDataBinder
    ){
        webDataBinder.addValidators(seriesValidator);
    }

    @GetMapping("/game/series/new")
    public String new_series(
            Model model
    ) {
        model.addAttribute("Series", new Series());
        return "/game/series/new";
    }

    @PostMapping("/game/series/new")
    public String new_series(
            Model model,
            @Valid @ModelAttribute("Series") Series series,
            BindingResult bindingResult
    ){
        seriesValidator.validate(series, bindingResult);

        if(!bindingResult.hasErrors()){
            seriesService.save(
                    series.getKorName(),
                    series.getOrgName(),
                    series.getNickname()
            );
            series.setKorName("");
            series.setOrgName("");
            series.setNickname("");
            model.addAttribute("success", true);
        }else{
            model.addAttribute("success", false);
        }
        return "/game/series/new";
    }

    @GetMapping("/game/series/list")
    public String series_list(
            Model model
    ){
        model.addAttribute("series_list", seriesService.findAll());
        return "/game/series/list";
    }
}
