package com.arcad.employeebook.service.impl;
public interface DepartmentServiceImpl {
    String printAllDepartment();

    /**
     * @param name Название отдела
     * @param salary Голый оклад по отделу
     * @return Добавляет новый отдел и возвращает краткий отчет по новой записи
     */
    String addDepartment(String name, String salary);
}
