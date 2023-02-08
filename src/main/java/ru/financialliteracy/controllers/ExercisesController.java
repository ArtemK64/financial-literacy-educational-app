package ru.financialliteracy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.financialliteracy.entities.Task;
import ru.financialliteracy.entities.Test;
import ru.financialliteracy.repositories.TaskRepository;
import ru.financialliteracy.repositories.TestRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExercisesController {
    private final TestRepository testRepository;
    private final TaskRepository taskRepository;
    @GetMapping("/exercises")
    public String exercisesPage() {
        return "exercises";
    }

    @GetMapping("/exercises/test")
    public String testPage(@ModelAttribute("test") Test test) {
        return "test";
    }

    @PostMapping("/exercises/test")
    public String submitTestPage(@ModelAttribute("test") @Valid Test test, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "test";
        }
        int countCorrectAns = test.countCorrectAnswers(List.of("Б", "В", "Б", "Б", "А"), test.getAllAnswers());
        test.setQtyOfCorrectAnswers(countCorrectAns);
        model.addAttribute("qtyOfCorrectAnswers", "Количество правильных ответов: " +
                countCorrectAns + ". Правильные ответы: «Б», «В», «Б», «Б», «А»");
        testRepository.save(test.removeSpacesFromAnswersBeforeSaving(test));
        return "test-results";
    }

    @GetMapping("/exercise/test/test-results")
    public String testResultPage() {
        return "test-results";
    }

    @GetMapping("/exercises/personal-finance-plan")
    public String personalFinancePlanPage(@ModelAttribute("task") Task task) {
        return "personal-finance-plan";
    }

    @PostMapping("/exercises/personal-finance-plan")
    public String personalFinancePlanResultPage(
            @ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "personal-finance-plan";
        }

        String answer = task.getAnswer().trim();
        task.setAnswer(answer);
        task.setNameOfTask("Personal finance plan");

        if ("86550".equals(answer)) {
            task.setIsAnswerCorrect("Yes");
            model.addAttribute("answer", "Вы дали правильный ответ");
        } else {
            task.setIsAnswerCorrect("No");
            model.addAttribute("answer", "Вы дали неправильный ответ. Верно: 86550");
        }
        taskRepository.save(task);
        return "task-results";
    }

    @GetMapping("/exercises/deposit")
    public String depositPage(@ModelAttribute("task") Task task) {
        return "deposit";
    }

    @PostMapping("/exercises/deposit")
    public String depositPageSubmit(
            @ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "deposit";
        }
        String answer = task.getAnswer().trim();
        task.setAnswer(answer);
        task.setNameOfTask("Deposit");

        if ("5".equals(answer)) {
            task.setIsAnswerCorrect("Yes");
            model.addAttribute("answer", "Вы дали правильный ответ");
        } else {
            task.setIsAnswerCorrect("No");
            model.addAttribute("answer", "Вы дали неправильный ответ. Верный ответ: 5");
        }
        taskRepository.save(task);
        return "task-results";
    }

    @GetMapping("/exercises/insurance")
    public String insurancePage(@ModelAttribute("task") Task task) {
        return "insurance";
    }

    @PostMapping("/exercises/insurance")
    public String insurancePageSubmit(
            @ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "insurance";
        }
        String answer = task.getAnswer().trim();
        task.setAnswer(answer);
        task.setNameOfTask("Insurance");

        if ("120000".equals(answer)) {
            task.setIsAnswerCorrect("Yes");
            model.addAttribute("answer", "Вы дали правильный ответ");
        } else {
            task.setIsAnswerCorrect("No");
            model.addAttribute("answer", "Вы дали неправильный ответ. Верный ответ: 120000");
        }
        taskRepository.save(task);
        return "task-results";
    }

    @GetMapping("/exercises/investment")
    public String investmentPage(@ModelAttribute("task") Task task) {
        return "investment";
    }

    @PostMapping("/exercises/investment")
    public String investmentPageSubmit(
            @ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "investment";
        }

        String answer = task.getAnswer().trim();
        task.setAnswer(answer);
        task.setNameOfTask("Investment");

        if ("1761".equals(answer)) {
            task.setIsAnswerCorrect("Yes");
            model.addAttribute("answer", "Вы дали правильный ответ");
        } else {
            task.setIsAnswerCorrect("No");
            model.addAttribute("answer", "Вы дали неправильный ответ. Верный ответ: 1761");
        }

        taskRepository.save(task);
        return "task-results";
    }

    @GetMapping("/exercises/pension")
    public String pensionPage(@ModelAttribute("task") Task task) {
        return "pension";
    }

    @PostMapping("/exercises/pension")
    public String pensionResultPage(
            @ModelAttribute("task") @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pension";
        }

        String answer = task.getAnswer().trim();
        task.setAnswer(answer);
        task.setNameOfTask("Pension");

        if ("9750".equals(answer)) {
            task.setIsAnswerCorrect("Yes");
            model.addAttribute("answer", "Вы дали правильный ответ");
        } else {
            task.setIsAnswerCorrect("No");
            model.addAttribute("answer", "Вы дали неправильный ответ. Верный ответ: 9750");
        }

        taskRepository.save(task);
        return "task-results";
    }
}