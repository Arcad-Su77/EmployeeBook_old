package com.arcad.employeebook.service.api;

import com.arcad.employeebook.dataproperties.DataProperties;
import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.AssertionErrors;

import java.util.List;
import java.util.Map;

//import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceImplTest {
    final DataProperties DP = new DataProperties();
    Map<Integer, Employee> emp;
    Map<Integer, Department> dep;
    @Mock
    DepartmentServiceImpl DSI;
    @InjectMocks
    EmployeeServiceImpl ESI;
    @BeforeEach
    void setUp() {
        emp = DataProperties.InitialEmployee();
        dep = DataProperties.InitialDep();
        System.out.println("SetUp emp = " + emp);
        System.out.println("dep = " + dep);
        DSI = new DepartmentServiceImpl(dep, ESI);
        ESI = new EmployeeServiceImpl(emp, DSI);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addEmploeey() {
    }

    @Test
    void employeeByIDDep() {
        List<Employee> lEmp_0 = ESI.EmployeeByIDDep("0");
        List<Employee> lEmp_1 = ESI.EmployeeByIDDep("1");
        List<Employee> lEmp_2 = ESI.EmployeeByIDDep("2");

        System.out.println("lEmp_0 = " + lEmp_0);
        System.out.println("lEmp_1 = " + lEmp_1);
        System.out.println("lEmp_2 = " + lEmp_2);

        AssertionErrors.assertTrue("Полный Список",(lEmp_0.size() == emp.size()));
        AssertionErrors.assertTrue("Список 1 отдела = 1 чел",lEmp_1.size()==1);
        AssertionErrors.assertTrue("Список 2 отдела = 1 чел",lEmp_2.size()==1);


    }
}