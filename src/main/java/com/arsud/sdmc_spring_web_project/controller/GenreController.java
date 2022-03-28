package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.entity.Genre;
import com.arsud.sdmc_spring_web_project.service.GenreService;
import com.arsud.sdmc_spring_web_project.validator.GenreValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreValidator genreValidator;
    private final GenreService genreService;

    @InitBinder("/game/genre/*")
    public void initBinder(
            WebDataBinder webDataBinder
    ){
        webDataBinder.addValidators(genreValidator);
    }

    @GetMapping("/game/genre/list")
    public String genre_list(
        Model model
    ){
        model.addAttribute("genre_list", genreService.findAll());
        return "/game/genre/list";
    }

    @GetMapping("/game/genre/new")
    public String new_genre(
            Model model
    ) {
        model.addAttribute("Genre", new Genre());
        return "/game/genre/new";
    }

    @PostMapping("/game/genre/new")
    public String new_genre(
            Model model,
            @Valid @ModelAttribute("Genre") Genre genre,
            BindingResult bindingResult
    ){
        genreValidator.validate(genre, bindingResult);

        if(!bindingResult.hasErrors()){
            genreService.save(genre.getName());
            genre.setName("");
            model.addAttribute("success", true);
        }else{
            model.addAttribute("success", false);
        }
        return "/game/genre/new";
    }
}
