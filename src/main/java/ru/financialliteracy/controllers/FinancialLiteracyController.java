package ru.financialliteracy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.financialliteracy.entities.Task;
import ru.financialliteracy.entities.Test;
import ru.financialliteracy.entities.User;
import ru.financialliteracy.repositories.TestRepository;

@Controller
@RequiredArgsConstructor
public class FinancialLiteracyController {
    private final TestRepository testRepository;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("welcomeCurrentUserName", "Привет, " + user.getFirstName());
        return "main";
    }

    @GetMapping("/statistics")
    public String statisticsPage(
            @AuthenticationPrincipal Test test, @AuthenticationPrincipal Task task, Model model) {

        model.addAttribute("testResults", "Правильных ответов: ");
        return "statistics";
    }
}