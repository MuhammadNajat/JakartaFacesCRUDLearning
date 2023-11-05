package com.example.jsf01.view;

import java.util.List;

import com.example.jsf01.model.Employee;
import com.example.jsf01.model.Message;
import com.example.jsf01.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
@Named @RequestScoped
public class EmployeeEnrolment {
    private Employee employee = new Employee();
    private List<Employee> employees;
    @Inject
    private EmployeeService employeeService;
    @PostConstruct
    public void init() {
        employees = employeeService.list();
    }
    public void submit() {
        employeeService.create(employee);
        employees.add(employee);
    }

    public Employee getEmployee() {
        return employee;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
}
