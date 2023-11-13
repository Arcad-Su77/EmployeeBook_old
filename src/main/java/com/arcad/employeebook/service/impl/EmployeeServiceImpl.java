package com.arcad.employeebook.service.impl;

import com.arcad.employeebook.service.exception.EmployeeAlreadyAddedException;

import java.util.List;


public interface EmployeeServiceImpl {

    String addEmploeey(List<String> args) throws EmployeeAlreadyAddedException;
}
