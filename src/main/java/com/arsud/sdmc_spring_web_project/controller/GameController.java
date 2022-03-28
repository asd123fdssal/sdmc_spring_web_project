package com.arsud.sdmc_spring_web_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GameController {

    @GetMapping("/game")
    public String game(){
        return "/game/game";
    }
}
