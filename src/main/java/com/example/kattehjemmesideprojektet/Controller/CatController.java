package com.example.kattehjemmesideprojektet.Controller;

import com.example.kattehjemmesideprojektet.Model.Cat;
import com.example.kattehjemmesideprojektet.Service.KattehjemmesideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CatController {
    @Autowired
    private KattehjemmesideService kattehjemmesideService;

    @GetMapping("/createCat")
    public String createCatForm(Model model) {
        model.addAttribute("cat", new Cat());
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
        System.out.println(kattehjemmesideService.findAllCats());
        return "index";
    }

    @GetMapping("/edit/{catId}")
    public String showEditForm(@PathVariable Long catId, Model model) {
        kattehjemmesideService.findCatById(catId).ifPresent(cat -> model.addAttribute("cat", cat));
        return "editCats";
    }

    @GetMapping("/delete/{catId}")
    public String deleteCat(@PathVariable Long catId) {
        kattehjemmesideService.deleteCatById(catId);
        return "redirect:/";
    }

}
