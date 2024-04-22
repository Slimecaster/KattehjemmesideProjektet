package com.example.kattehjemmesideprojektet.Controller;

import com.example.kattehjemmesideprojektet.Model.User;
import com.example.kattehjemmesideprojektet.Service.KattehjemmesideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private KattehjemmesideService kattehjemmesideService;

    @GetMapping("/createUser")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user){
        kattehjemmesideService.createUser(user);
        return "redirect:/";
    }



}

