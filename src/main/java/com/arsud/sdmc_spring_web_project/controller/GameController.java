package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class GameController {

    @GetMapping("/game")
    public String game(){
        return "/game/game";
    }

}
