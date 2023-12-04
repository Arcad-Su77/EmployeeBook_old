package com.arcad.employeebook.service.impl;

import com.arcad.employeebook.elementaryClasses.Department;

public interface DepartmentService {
    String printAllDepartment(String idd);

    /**
     * @param name Название отдела
     * @param salary Голый оклад по отделу
     * @return Добавляет новый отдел и возвращает краткий отчет по новой записи
     */
    String addDepartment(String name, String salary);

    Department getDepartment(Integer departmentID);
}
