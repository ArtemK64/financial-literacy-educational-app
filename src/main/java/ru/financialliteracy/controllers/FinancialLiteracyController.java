package ru.financialliteracy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FinancialLiteracyController {
    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }
}