package org.example.service;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.EmployeeAlreadyAddedException;
import org.example.exception.EmployeeBadRequestException;
import org.example.exception.EmployeeNotFoundException;
import org.example.exception.EmployeeStorageIsFullException;
import org.example.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static int MAX_EMPLOYEES = 10;

    private final Map<String, Employee> employees = new HashMap<>(Map.of(
            "Ivan Ivanov", new Employee("Ivan", "Ivanov", 2000, 1),
            "Petr Petrov", new Employee("Petr", "Petrov", 2500, 1),
            "Harry Potter", new Employee("Harry", "Potter", 3000, 2),
            "Olga Simonova", new Employee("Olga", "Simonova", 3000, 2),
            "Anastasia Zvyagina", new Employee("Anastasia", "Zvyagina", 5000, 3),
            "Karina Smirnova", new Employee("Karina", "Smirnova", 5500, 3)
    ));

    public Employee add(String firstName, String lastName, double salary, int department)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {

        Employee employee = new Employee(firstName, lastName, salary, department);

        validateInput(firstName, lastName);

        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("The list size is exceeded!!!");
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Such an employee already exists!!!");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName, double salary, int department)
            throws EmployeeNotFoundException {

        Employee employee = new Employee(firstName, lastName, salary, department);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("This employee has not been found");
        }
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName, double salary, int department)
            throws EmployeeNotFoundException {

        Employee employee = new Employee(firstName, lastName, salary, department);

        if (employees.containsKey(employee.getFullName())) {
            return employee;
        }
        throw new EmployeeNotFoundException("This employee has not been found");
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public void validateInput(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmployeeBadRequestException("Incorrect input!!!");
        }
    }
}
