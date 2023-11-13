package com.arcad.employeebook.service.api;

import com.arcad.employeebook.dataproperties.DataProperties;
import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.service.exception.EmployeeAlreadyAddedException;
import com.arcad.employeebook.service.impl.DepartmentServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DepartmentService implements DepartmentServiceImpl {
    private static Map<Integer, Department> departments;

    public DepartmentService() {
        this.departments = DataProperties.InitialDep();
    }

    public static Map<Integer, Department> getDepartments() {
        return departments;
    }

    @Override
    public String printAllDepartment() {
        String result="<tr><th>ID</th><th>Оклад отдела</th><th>Наименование отдела</th></tr>";
        for (Map.Entry<Integer, Department> depMap : departments.entrySet()) {
            if (depMap != null) {
                result += "<tr><td>" + depMap.getValue().getDepartmentID() +
                        "</td><td>" + depMap.getValue().getSalary() +
                        "</td><td>" + depMap.getValue().getName() + "</td></tr>";
            }
        }
        return result;
    }

    /**
     * @param name
     * @param salary
     * @return
     */
    @Override
    public String addDepartment(String name, String salary) {
        String result="<tr><th>ID</th><th>Оклад отдела</th><th>Наименование отдела</th></tr>";
        Double doSalary = Double.valueOf(salary);

        if (name.isEmpty() && (salary.isEmpty() || doSalary.isNaN())) {
            result += "<tr><td>" + "Параметры не верные" +
                    "</td><td>" + salary +
                    "</td><td>" + name + "</td></tr>";
        } else {
            Department addNew = new Department(name, doSalary);
            Department depNew = departments.putIfAbsent(addNew.hashCode(), addNew);
            if (depNew == null) {
                result += "<tr><td>" + addNew.getDepartmentID() +
                                "</td><td>" + addNew.getSalary() +
                                "</td><td>" + addNew.getName() + "</td></tr>";
            } else {
                Department.decCount();
                throw new EmployeeAlreadyAddedException("Такой отдел уже добавлен!!! \n" + addNew);
            }
        }
        return result;
    }
}
