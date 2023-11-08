package com.example.jsf01.service;

import com.example.jsf01.model.Employee;
import com.example.jsf01.repository.EmployeeRepository;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
