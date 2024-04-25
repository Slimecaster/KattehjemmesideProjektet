package com.example.kattehjemmesideprojektet.Controller;

import com.example.kattehjemmesideprojektet.Model.User;
import com.example.kattehjemmesideprojektet.Service.KattehjemmesideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        return "redirect:/userCreatedSucces";
    }
    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/userCreatedSucces")
    public String userCreatedSucces(Model model) {
        model.addAttribute("user", new User());
        return "userCreatedSucces";
    }

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



    @GetMapping("/searchtestUser")
    public String showUser(@RequestParam(required = false) Long userId, Model model, User u) {
        if (userId != null) {
            Optional<User> userOptional = kattehjemmesideService.findUserById(userId);
            userOptional.ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("userFound", userOptional.isPresent()); // Add attribute indicating if user was found
        }
        return "searchUser";
    }



    @PostMapping("/login")

    public String login(@ModelAttribute User user, Model model) {
        boolean authenticated = kattehjemmesideService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticated) {
            return "redirect:/menu"; // Omled til hovedsiden hvis login er succesfuldt
        } else {
            model.addAttribute("error", "Ugyldigt brugernavn eller password");
            return "login"; // Bliv på login siden hvis login fejler
        }
    }

}

