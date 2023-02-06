package ru.financialliteracy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.financialliteracy.entities.User;

import javax.validation.Valid;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "login";
    }
}