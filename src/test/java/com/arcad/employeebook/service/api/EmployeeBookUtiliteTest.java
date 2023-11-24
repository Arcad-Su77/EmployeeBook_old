package com.arcad.employeebook.service.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class EmployeeBookUtiliteTest {

    private final EmployeeBookUtilite ebu = new EmployeeBookUtilite();

//    EmployeeBookUtiliteTest(EmployeeBookUtilite ebu) {
//        this.ebu = ebu;
//    }

    @BeforeEach
    void setUp() {
        System.out.println("Приступим к тесту");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Тестирование завершено");
    }

    @Test
    void isReqParamString() {
        String firstName = "asd";
        String lastName = "s##fgr";
        String midleName = "gbgb";
        List<String> argsString = new java.util.ArrayList<>(List.of(firstName, lastName, midleName));
        System.out.println("ebu.isReqParamString(argsString) = " + ebu.isReqParamString(argsString));
    }

    @Test
    void isReqParamNum() {
    }
}