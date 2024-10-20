package org.example.controller;

import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("salary") double salary,
                                @RequestParam("department") int department) {
        return employeeService.add(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("salary") double salary,
                                   @RequestParam("department") int department) {
        return employeeService.remove(firstName, lastName, salary, department);
    }


    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("salary") double salary,
                                 @RequestParam("department") int department) {
        return employeeService.find(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }
}

