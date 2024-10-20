package org.example.service;

import org.example.exception.EmployeeAlreadyAddedException;
import org.example.exception.EmployeeBadRequestException;
import org.example.exception.EmployeeNotFoundException;
import org.example.exception.EmployeeStorageIsFullException;
import org.example.model.Employee;

import java.util.List;


public interface EmployeeService {

    Employee add(String firstName, String lastName, double salary, int department)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException;

    Employee remove(String firstName, String lastName, double salary, int department)
            throws EmployeeNotFoundException;

    Employee find(String firstName, String lastName, double salary, int department)
            throws EmployeeNotFoundException;

    void validateInput(String firstName, String lastName)
            throws EmployeeBadRequestException;

    List<Employee> getAll();


}
