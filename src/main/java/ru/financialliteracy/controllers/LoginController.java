package ru.financialliteracy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.financialliteracy.entities.User;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "login";
    }
}