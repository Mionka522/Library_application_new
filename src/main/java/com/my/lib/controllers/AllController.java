package com.my.lib.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllController {
    @GetMapping()
    public String all(){
        return "/all";
    }
}
