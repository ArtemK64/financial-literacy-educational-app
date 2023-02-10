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
    public String statisticsPage(@AuthenticationPrincipal User user, Task task, Test test, Model model) {
        List<Test> testList = testRepository.findAll()
                .stream()
                .filter(t -> t.getUser()
                        .getEmail()
                        .equalsIgnoreCase(user.getEmail())).toList();

        if (testList.size() > 0) {
            model.addAttribute("testResults",
                    "Ваш лучший результат по общему тесту. Количество правильных ответов: " +
                            test.getBestTestResults(testList));
        } else {
            model.addAttribute("testResults", "Вы не решали общий тест");
        }

        model.addAttribute("financesTaskResults",
                task.getBestResult(taskRepository.findAll(), "Finances", user));
        model.addAttribute("depositTaskResults",
                task.getBestResult(taskRepository.findAll(), "Deposit", user));
        model.addAttribute("insuranceTaskResults",
                task.getBestResult(taskRepository.findAll(), "Insurance", user));
        model.addAttribute("investmentTaskResults",
                task.getBestResult(taskRepository.findAll(), "Investment", user));
        model.addAttribute("pensionTaskResults",
                task.getBestResult(taskRepository.findAll(), "Pension", user));

        return "statistics";
    }

    @GetMapping("/about")
    public String aboutUsPage() {
        return "about";
    }

    @GetMapping("/video")
    public String videoContentPage() {
        return "video";
    }
}