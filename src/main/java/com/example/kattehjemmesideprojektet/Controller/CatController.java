package com.example.kattehjemmesideprojektet.Controller;

import com.example.kattehjemmesideprojektet.Model.Cat;
import com.example.kattehjemmesideprojektet.Service.KattehjemmesideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CatController {
    @Autowired
    private KattehjemmesideService kattehjemmesideService;

    @GetMapping("/createCat")
    public String createCatForm(Model model) {
        model.addAttribute("createCat", new Cat());
        return "createCat";
    }
    @PostMapping("/createCat")
    public String createCat(@ModelAttribute Cat cat){
        kattehjemmesideService.createCat(cat);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showAllCats(Model model) {
        model.addAttribute("cats", kattehjemmesideService.findAllCats());
        return "index";
    }

}
