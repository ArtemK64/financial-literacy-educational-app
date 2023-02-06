package ru.financialliteracy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.financialliteracy.entities.User;
import ru.financialliteracy.repositories.UserRepository;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserRepository userRepository;

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping("/register")
    public String registerPageSubmit(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "redirect:/login";
    }
}