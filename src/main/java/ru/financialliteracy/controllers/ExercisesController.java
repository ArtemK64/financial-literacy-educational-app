package ru.financialliteracy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.financialliteracy.entities.Test;
import ru.financialliteracy.entities.User;
import ru.financialliteracy.repositories.TestRepository;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ExercisesController {
    private final TestRepository testRepository;
    @GetMapping("/exercises")
    public String exercisesPage() {
        return "exercises";
    }

    @GetMapping("/exercises/test")
    public String testPage(@ModelAttribute("test") Test test) {
        return "test";
    }

    @PostMapping("/exercises/test")
    public String submitTestPage(@ModelAttribute("test") @Valid Test test, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "test";
        }
        testRepository.save(test);
        return "test";
    }
}
