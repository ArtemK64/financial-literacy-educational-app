package ru.financialliteracy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.financialliteracy.entities.Task;
import ru.financialliteracy.entities.Test;
import ru.financialliteracy.entities.User;
import ru.financialliteracy.repositories.TaskRepository;
import ru.financialliteracy.repositories.TestRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FinancialLiteracyController {
    private final TestRepository testRepository;
    private final TaskRepository taskRepository;
    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("welcomeCurrentUserName", "Здравствуйте, " + user.getFirstName());
        return "main";
    }

    @GetMapping("/statistics")
    public String statisticsPage(Test test, Model model) {
        if (testRepository.count() > 0) {
            model.addAttribute("testResults",
                    "Ваш лучший результат по общему тесту. Количество правильных ответов: " +
                            test.getBestTestResults(testRepository.findAll()));
        } else {
            model.addAttribute("testResults", "Вы не решали общий тест");
        }

        if (taskRepository.count() > 0) {

        }

        return "statistics";
    }
}