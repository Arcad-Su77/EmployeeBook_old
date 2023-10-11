package com.arcad.employeebook.controller;

import com.arcad.calculator.service.api.CalculatorService;
import com.arcad.employeebook.service.view.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathController {
    private final CalculatorService calculatorService;
    private final ViewService viewService;

    public PathController(CalculatorService calculatorService, ViewService viewService) {
        this.calculatorService = calculatorService;
        this.viewService = viewService;
    }
    @GetMapping(path = "/calculator/plus")
    public String plus(@RequestParam("num1") String num1, @RequestParam("num2") String num2) {
        String[] args = {num1, num2};
        String result = " Ошибка в исходных данных.";
        if (calculatorService.isCheckParam(args)) {
            result = calculatorService.plus(args);
        }
        return viewService.viewOut(("Plus: num1=" + num1 + ", num2=" + num2 + ", Resultat=" + result));
    }
    @GetMapping(path = "/calculator/minus")
    public String minus(@RequestParam("num1") String num1, @RequestParam("num2") String num2) {
        String[] args = {num1, num2};
        String result = "Ошибка в исходных данных.";
        if (calculatorService.isCheckParam(args)) {
            result = calculatorService.minus(args);
        }
        return viewService.viewOut(("Minus: num1=" + num1 + ", num2=" + num2 + ", Resultat=" + result));
    }
    //    /calculator/multiply?num1=5&num2=5
    @GetMapping(path = "/calculator/multiply")
    public String multiply(@RequestParam("num1") String num1, @RequestParam("num2") String num2) {
        String[] args = {num1, num2};
        String result = " Ошибка в исходных данных.";
        if (calculatorService.isCheckParam(args)) {
            result = calculatorService.multiply(args);
        }
        return viewService.viewOut(("Multiply: num1=" + num1 + ", num2=" + num2 + ", Resultat=" + result));
    }
    //    /calculator/divide?num1=5&num2=5
    @GetMapping(path = "/calculator/divide")
    public String divide(@RequestParam("num1") String num1, @RequestParam("num2") String num2) {
        String[] args = {num1, num2};
        String result = " Ошибка в исходных данных.";
        if (calculatorService.isCheckParam(args)) {
            result = calculatorService.divide(args);
        }
        return viewService.viewOut(("Divide: num1=" + num1 + ", num2=" + num2 + ", Resultat=" + result));
    }
}
