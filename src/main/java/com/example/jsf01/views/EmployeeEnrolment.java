package com.example.jsf01.views;

import java.io.IOException;
import java.util.List;

import com.example.jsf01.models.entities.Employee;
import com.example.jsf01.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
@Named @RequestScoped
public class EmployeeEnrolment {
    private Employee employee = new Employee();
    private List<Employee> employees;
    @Inject
    private EmployeeService employeeService;

    @Inject
    private ExternalContext externalContext;
    @PostConstruct
    public void init() {
        try {
            employees = employeeService.findAll();
        } catch(Exception e) {
            e.printStackTrace();
            employees = null;
        }
    }

    public void submit() throws IOException {
        employeeService.create(employee);
        employees.add(employee);

        externalContext.redirect("/JSF01-1.0-SNAPSHOT/home.xhtml");
    }

    public Employee getEmployee() {
        return employee;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
}
