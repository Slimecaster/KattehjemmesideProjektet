package com.example.kattehjemmesideprojektet.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/menu")
    public String showMenu(){
        return "menu";
    }

}
