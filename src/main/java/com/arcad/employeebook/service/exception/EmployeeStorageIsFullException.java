package com.arcad.employeebook.service.exception;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(String message) {
        super(message);
        System.out.println("Исключение EmployeeStorageIsFullException !!!\n" + message);
    }
}
