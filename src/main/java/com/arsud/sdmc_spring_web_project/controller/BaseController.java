package com.arsud.sdmc_spring_web_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/index")
    public String toIndex(){
        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

}
