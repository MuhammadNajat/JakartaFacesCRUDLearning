package com.example.jsf01.view;

import java.io.IOException;
import java.util.List;

import com.example.jsf01.model.Employee;
import com.example.jsf01.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
@Named @RequestScoped
public class EmployeeEnrolment {
    private Employee employee = new Employee();
    private List<Employee> employees;
    @Inject
    private EmployeeService employeeService;

    //@ManagedProperty("#{facesContext.externalContext}")
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
        //employeeService.updateEmployee(employee);
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
