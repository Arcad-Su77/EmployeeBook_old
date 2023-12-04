package com.arcad.employeebook.service.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeBookUtiliteTest {

    private final EmployeeBookUtilite ebu = new EmployeeBookUtilite();

    @BeforeEach
    void setUp() {
        System.out.println("=================\nПриступим к тесту\n==================");
    }

    @AfterEach
    void tearDown() {
        System.out.println("=================\nТестирование завершено\n=================");
    }

    @Test
    void isReqParamString() {
        String firstName = "asd";
        String lastName = "sjh78fgr";
        String midleName = "gbgb";
        List<String> argsString = new java.util.ArrayList<>(List.of(firstName, lastName, midleName));
        System.out.println("ebu.isReqParamString("+argsString+") = " + ebu.isReqParamString(argsString));
        assertTrue(ebu.isReqParamString(argsString));
    }

    @Test
    void isReqParamNum() {
    }
}