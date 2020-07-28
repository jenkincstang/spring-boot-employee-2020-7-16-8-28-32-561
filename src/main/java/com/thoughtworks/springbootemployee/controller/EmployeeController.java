package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findEmployeeById(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }


    @GetMapping("/employees")
    public List<Employee> findEmployeesByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<Employee> employees = employeeService.findAllEmployees();
        List<Employee> pagedEmployees = new ArrayList<>();

        int startRow = (page - 1) * pageSize;
        int endRow = startRow + pageSize - 1;
        for (int index = startRow; index < employees.size() && index < endRow; index++) {
            pagedEmployees.add(employees.get(index));
        }
        return pagedEmployees;
    }

    @GetMapping("/employees")
    public List<Employee> findEmployeesByGender(@RequestParam("gender") String gender) {
        List<Employee> employees = employeeService.findAllEmployees();
        List<Employee> employeesWithGender = new ArrayList<>();

        for (int index = 0; index < employees.size(); index++) {
            if (employees.get(index).getGender().equals(gender))
                employeesWithGender.add(employees.get(index));
        }
        return employeesWithGender;
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }
}
