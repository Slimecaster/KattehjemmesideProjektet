package com.example.kattehjemmesideprojektet.Controller;

import com.example.kattehjemmesideprojektet.Model.User;
import com.example.kattehjemmesideprojektet.Service.KattehjemmesideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    /*@PostMapping("/createUser")
    public String createUser(@ModelAttribute User user){
        kattehjemmesideService.createUser(user);
        return "redirect:/";
    }*/

    @GetMapping("/indexUser")
    public String showAllUsers(Model model) {
        model.addAttribute("users", kattehjemmesideService.findAllUsers());
        return "indexUser";
    }

    @GetMapping("/indexUser/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        kattehjemmesideService.deleteUserById(userId);
        return "redirect:/indexUser";
    }

    @GetMapping("/indexUser/edit/{userId}")
    public String showEditForm(@PathVariable Long userId, Model model) {
        kattehjemmesideService.findUserById(userId).ifPresent(user -> model.addAttribute("user", user));
        return "editUsers";
    }

    @PostMapping("/createUser")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        kattehjemmesideService.createUser(user);
        return "/menu";
    }



}

