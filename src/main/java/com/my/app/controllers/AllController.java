package com.my.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllController {
    //Начальная страница выбора списков
    @GetMapping()
    public String all(){
        return "/all";
    }
}
