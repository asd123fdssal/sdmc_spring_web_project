package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.entity.Characters;
import com.arsud.sdmc_spring_web_project.entity.Title;
import com.arsud.sdmc_spring_web_project.service.CharactersService;
import com.arsud.sdmc_spring_web_project.service.TitleService;
import com.arsud.sdmc_spring_web_project.validator.CharactersValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequiredArgsConstructor
public class CharactersController {

    private final CharactersValidator charactersValidator;
    private final CharactersService charactersService;
    private final TitleService titleService;

    @GetMapping("/game/title/characters/new/{id}")
    public String new_character(
            Model model,
            @PathVariable Long id
    ){
        model.addAttribute("Characters", new Characters());
        model.addAttribute("Title", titleService.findById(id));

        return "/game/title/characters/new";
    }

    @PostMapping("/game/title/characters/new")
    public String new_charcter(
            @Valid @ModelAttribute("Character") Characters characters,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            HttpServletResponse response
    ) throws IOException {
        Title title = titleService.findById(characters.getId());
        if(characters.getKorName().indexOf(",") > 0){
            characters.setKorName(characters.getKorName().split(",")[1]);
        }
        charactersValidator.validate(characters, bindingResult);

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

        charactersService.save(
            title,
                image,
                characters.getKorName(),
                characters.getOrgName(),
                characters.getStrategy()
        );

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>self.close()</script>");
        writer.flush();
        return "";
    }

    @GetMapping("/game/title/characters/detail/{id}")
    public String character_strategy(
        Model model,
        @PathVariable Long id
    ){
        Characters characters = charactersService.findById(id);
        model.addAttribute("Characters", characters);
        return "/game/title/characters/detail";
    }

}
