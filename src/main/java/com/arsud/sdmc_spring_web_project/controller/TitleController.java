package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.dto.GenreList;
import com.arsud.sdmc_spring_web_project.entity.*;
import com.arsud.sdmc_spring_web_project.service.*;
import com.arsud.sdmc_spring_web_project.utils.ImageUtilty;
import com.arsud.sdmc_spring_web_project.validator.TitleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TitleController {

    private final ImageUtilty imageUtilty;
    private final TitleValidator titleValidator;

    private final TitleService titleService;
    private final CompanyService companyService;
    private final GenreService genreService;
    private final CharactersService charactersService;
    private final SeriesService seriesService;
    private final TitleHasGenreService titleHasGenreService;

    @GetMapping("/game/title/list")
    public String game(
            Model model
    ){
        List<Title> tList = titleService.findAll();
        tList.forEach(t -> t.setEncodeImage(imageUtilty.makeBase64Image(t.getPicture())));
        model.addAttribute("title_list", titleService.findAll());
        return "/game/title/list";
    }

    @GetMapping("/game/title/new")
    public String game_new(
            Model model
    ){
        model.addAttribute("Title", new Title());
        model.addAttribute("Company", companyService.findAll());
        model.addAttribute("Series", seriesService.findAll());
        return "/game/title/new";
    }

    @GetMapping("/game/title/detail/{id}")
    public String game_detail(
            Model model,
            @PathVariable Long id
    ){
        Title title = titleService.findById(id);
        title.setEncodeImage(imageUtilty.makeBase64Image(title.getPicture()));
        title.setCompany_name(title.getCompany().getKorName());
        List<Characters> charactersList = charactersService.findAllByTitle(title);
        if(charactersList != null) {
            charactersList.forEach(l -> l.setEncodeImage(imageUtilty.makeBase64Image(l.getPicture())));
        }

        List<GenreList> gl = genreService.findGenreListByTitleId(title.getId());
        List<Genre> genreList = new ArrayList<Genre>();
        gl.forEach(g -> genreList.add(g.toGenre()));
        title.setGenre_list_str(Arrays.toString(genreList.toArray()).replace("[","").
                replace("]","").replace(" ", ""));

        model.addAttribute("Company", companyService.findAll());
        model.addAttribute("Title", title);
        model.addAttribute("character_list", charactersList);
        return "/game/title/detail";
    }

    @PostMapping("/game/title/new")
    public String game_new(
            @Valid @ModelAttribute("Title") Title title,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file
            ){
        titleValidator.validate(title, bindingResult);

        title.setReleaseDate(LocalDate.parse(title.getReleaseDate_str(), DateTimeFormatter.ISO_DATE));

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

        Series series = seriesService.findByKorName(title.getSeries_name());
        String genre_str = title.getGenre_list_str();

        title = titleService.save(
            title.getCompany(),
                series,
                image,
                title.getReleaseDate(),
                title.getKorName(),
                title.getOrgName(),
                title.getHookCode(),
                title.getEtc()
        );

        if(!genre_str.isBlank()){
            String[] genre_list = genre_str.split(",");
            List<Genre> gList = new ArrayList<Genre>();
            Arrays.stream(genre_list).forEach(g -> gList.add(genreService.findByName(g)));
            titleHasGenreService.save(title.getId(), gList);
        }


        return "redirect:/game/title/list";
    }

    @GetMapping("/game/title/sel_genre")
    public String sel_genre(
            Model model
    ){
        model.addAttribute("genre_list", genreService.findAll());
        return "/game/title/sel_genre";
    }

}
