package ru.financialliteracy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.financialliteracy.entities.User;
import ru.financialliteracy.repositories.UserRepository;
import ru.financialliteracy.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

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

        if (!userService.isEmailExist(user.getEmail())) {
            userRepository.save(user);
            return "redirect:/login";
        }
        return "registration-err";
    }

    @GetMapping("/registration-err")
    public String registrationErrPage() {
        return "registration-err";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "login";
    }
}