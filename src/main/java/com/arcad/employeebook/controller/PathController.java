package com.arcad.employeebook.controller;

import com.arcad.employeebook.service.api.EmployeeBook;
import com.arcad.employeebook.view.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathController {
//    private final EmployeeBook employeeBook;
//    private final ViewService viewService;
//
//    public PathController(EmployeeBook employeeBook, ViewService viewService) {
//        this.employeeBook = employeeBook;
//        this.viewService = viewService;
//    }
//
//     switch ((MENU_ROUTER +taskNumberRun)) {
//        case 0 -> EXIT = false;
//        case 1 -> employeeBook.printAllEmployee();    //Список сотрудников
//        case 2 -> System.out.println("Сумма затрат на зарплату: "  + employeeBook.getSumSalary());
//        case 3 -> System.out.println("Сотрудник с минимальной зарплатой: " +
//                employeeBook.getEmployee(employeeBook.findEmployeeMiniSalary()));
//        case 4 -> System.out.println("Сотрудник с средней зарплатой: " +
//                employeeBook.getEmployee(employeeBook.findEmployeeMidleSalary()));
//        case 5 -> System.out.println("Сотрудник с максимальной зарплатой: " +
//                employeeBook.getEmployee(employeeBook.findEmployeeMaxiSalary()));
//        case 6 -> System.out.println("Средня зарплата сотрудников: " + employeeBook.getSumSalary() / Employee.getCount());
//        case 7 -> menuRoute(7);
//        case 8 -> employeeBook.printAllDepartment();
//        case 9 -> employeeBook.editDepartment(scan);
//        case 10 -> employeeBook.printAllEmployee();
//        case 11 -> employeeBook.editEmployee(scan);
//        case 12 -> employeeBook.printAllDepartment();
//        case 13 -> employeeBook.editDepartment(scan);
//        default -> System.out.println("Вы не выбрали задание [1-0]1: " + taskNumberRun);
//    }
//                if (MENU_ROUTER == 0)
//            if (EXIT) {
//        System.out.println("\n=====================\nДля выхода в меню введите любой символ.");
//        scan.nextLine();
//    } else {
//        System.out.println("\n=====================\nСпасибо за использование нашего продукта.\n==============");
//    }
//
    @GetMapping(path = "/Employee/printAll")
//    @RequestParam("num1") String num1, @RequestParam("num2") String num2
    public String printAllEmployee(EmployeeBook employeeBook, ViewService viewService) {
        String result = employeeBook.printAllEmployee();    //Список сотрудников
        return viewService.viewOutTable("Список всех сотрудников", result);
    }
}
