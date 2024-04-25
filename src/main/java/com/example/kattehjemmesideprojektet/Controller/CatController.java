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
        return "redirect:/indexCats";
    }

    @GetMapping("/indexCats")
    public String showAllCats(Model model) {
        model.addAttribute("cats", kattehjemmesideService.findAllCats());
        return "indexCats";
    }

    @GetMapping("/edit/{catId}")
    public String showEditForm(@PathVariable Long catId, Model model) {
        kattehjemmesideService.findCatById(catId).ifPresent(cat -> model.addAttribute("cat", cat));
        return "editCats";
    }

    @GetMapping("/delete/{catId}")
    public String deleteCat(@PathVariable Long catId) {
        kattehjemmesideService.deleteCatById(catId);
        return "redirect:/indexCats";
    }


    @GetMapping("/earchCatById")
    public String showCat(@RequestParam(required = false) Long catId, Model model) {
        if (catId != null) {
            Optional<Cat> catOptional = kattehjemmesideService.findCatById(catId);
            catOptional.ifPresent(cat -> model.addAttribute("cat", cat));
            model.addAttribute("catFound", catOptional.isPresent()); // Add attribute indicating if cat was found
        }
        return "search-form";
    }

    @GetMapping("/searchCatByOwner")
    public String showCatsByOwner(@RequestParam(required = false) Long owner, Model model) {
        if (owner != null) {
            List<Cat> cats = kattehjemmesideService.findCatsByUser(owner);
            model.addAttribute("cats", cats);
            model.addAttribute("catFound", !cats.isEmpty()); // Add attribute indicating if cats were found
        }
        return "searchCatByOwner";
    }

}
