package ru.financialliteracy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TheoryController {
    @GetMapping("/theory")
    public String theoryPage() {
        return "theory";
    }

    @GetMapping("/theory/beginning")
    public String beginningPage() {
        return "beginning";
    }

    @GetMapping("/theory/finance-tools")
    public String financeToolsPage() {
        return "finance-tools";
    }

    @GetMapping("/theory/low-risks")
    public String lowRisksPage() {
        return "low-risks";
    }

    @GetMapping("/theory/entrepreneurship")
    public String entrepreneurshipPage() {
        return "entrepreneurship";
    }
}