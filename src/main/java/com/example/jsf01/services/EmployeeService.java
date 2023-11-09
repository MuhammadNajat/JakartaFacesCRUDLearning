package com.example.jsf01.services;

import com.example.jsf01.models.entities.Employee;
import com.example.jsf01.models.repositories.EmployeeRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class EmployeeService {
    @Inject
    private EmployeeRepository employeeRepository;

    @Transactional
    public void create(Employee employee) {
        System.out.println("### *** Entered EmployeeService::create");
        employeeRepository.create(employee);
    }

    @Transactional
    public Employee update(Employee employee) {
        System.out.println("### *** Entered EmployeeService::update");
        return employeeRepository.update(employee);
    }

    @Transactional
    public void delete(Employee employee) {
        System.out.println("### *** Entered EmployeeService::delete");
        employeeRepository.delete(employee);
    }

    @Transactional
    public List<Employee> findAll() {
        System.out.println("### *** Entered EmployeeService::findAll");
        List<Employee> employees;
        try {
            employees = employeeRepository.findAll().get();
        } catch(Exception e) {
            e.printStackTrace();
            return  null;
        }
        return employees;
    }
}
