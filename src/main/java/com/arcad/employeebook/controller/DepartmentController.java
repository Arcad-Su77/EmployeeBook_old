package com.arcad.employeebook.controller;

import com.arcad.employeebook.service.api.DepartmentService;
import com.arcad.employeebook.service.api.EmployeeBookUtilite;
import com.arcad.employeebook.view.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Department")
public class DepartmentController {

    private final ViewService viewService;
    private final DepartmentService departmentService;
    private final EmployeeBookUtilite employeeBookUtilite;

    public DepartmentController(ViewService viewService, DepartmentService departmentService, EmployeeBookUtilite employeeBookUtilite) {
        this.viewService = viewService;
        this.departmentService = departmentService;
        this.employeeBookUtilite = employeeBookUtilite;
    }

    @GetMapping(path = "/printAll")
//    @RequestParam("num1") String num1, @RequestParam("num2") String num2
    public String printAllDepartment() {
        String result = departmentService.printAllDepartment();    //Список сотрудников
        return viewService.viewOutTable("Список отделов", result);
    }

    @GetMapping(path = "/add")
    public String addDepartment(@RequestParam("name") String name,
                                @RequestParam("salary") String salary) {
        String result;
        List<String> argsString = new java.util.ArrayList<>(List.of(name));
        List<String> argsNumb = List.of(salary);
        if (employeeBookUtilite.isReqParamString(argsString) &
                employeeBookUtilite.isReqParamNum(argsNumb)) {
            argsString.addAll(argsNumb);
            result = departmentService.addDepartment(name, salary);    //Список сотрудников
        } else {
            argsString.addAll(argsNumb);
            result = "<tr><td> Введены не верные данные </td></tr>" +
                    "<tr><td>" + argsString + "</td></tr>";
        }
        return viewService.viewOutTable("Добавляем отдел", result);
    }
}
