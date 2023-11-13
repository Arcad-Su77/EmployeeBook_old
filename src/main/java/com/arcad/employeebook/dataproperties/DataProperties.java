package com.arcad.employeebook.dataproperties;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.service.api.DepartmentService;
import com.arcad.employeebook.service.api.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataProperties implements CommandLineRunner {
    private Map<Integer, Department> initDepartment;
    private Map<Integer, Employee> initEmployees;

    public static String[][] inEmployee = {
//            Фамилия   Имя     Отчество    №Отдела Коэф.Оклада
            {"Иванов", "Иван", "Иванович", "1", "1.2"},
            {"Иванов", "Иван", "Кузминович", "2", "1.25"},
            {"Иванов", "Фома", "Ильич", "3", "1.12"},
            {"Семенов", "Иван", "Иванович", "4", "1.05"},
            {"Фролов", "Сергей", "Фомич", "5", "1.01"}
    };
    public static String[][] inDepartment = {
//            Имя   Зарплата
            {"АУП", "200000"},
            {"Бухгалтерия", "170000"},
            {"Front-разработчики", "150000"},
            {"Back-разработчики", "140000"},
            {"Тестировщики", "120000"}
    };
    public static String menu_0 = "Выберете действие:\n" +
            "1. Список сотрудников.\n" +
            "2. Сумма затрат на зарплаты.\n" +
            "3. Найти сотрудника с минимальной заплатой.\n" +
            "4. Найти сотрудника с средней заплатой.\n" +
            "5. Найти сотрудника с максимальной зарплатой.\n" +
            "6. Подсчитать среднее значение зарплат.\n" +
            "7. Управление отделами и сотрудниками.\n" +
            "0. Завершить программу";
    public static String menu_1 = "Выберете действие для работы с отделами:\n" +
            "1. Вывести список отделов.\n" +
            "2. Изменить значение отдела.\n" +
            "3. Вывести список сотрудников.\n" +
            "4. Изменить значение сотрудника.\n" +
            "5. Добавить нового сотрудника.\n" +
            "6. Удалить сотрудника.\n" +
            "0. Завершить программу";

    @Override
    public void run(String... args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        DepartmentService departmentService = context.getBean(
                "DepartmentService", DepartmentService.class);
        EmployeeService employeeService = context.getBean(
                "EmploeeyService", EmployeeService.class);


        context.close();
    }

    public static Map<Integer, Employee> InitialEmployee() {
        Map<Integer, Employee> initEmployees = new HashMap<Integer, Employee>();
        for (String[] strings : inEmployee) {
            int depID = Integer.parseInt(strings[3]);
            float salaryRate = Float.parseFloat(strings[4]);
            initEmployees.put(Employee.getCount()+1, new Employee(strings[0], strings[1], strings[2],
                    depID, salaryRate));
        }
        return initEmployees;
    }

    public static Map<Integer, Department> InitialDep() {
        int i = 1;
        Map<Integer, Department> initDepartment = new HashMap<>();
        for (String[] strings : inDepartment) {
            int payment = Integer.parseInt(strings[1]);
            initDepartment.put(i, new Department(strings[0], payment));
            i++;
        }
        return initDepartment;
    }
}
