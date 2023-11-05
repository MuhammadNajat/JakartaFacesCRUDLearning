package com.example.jsf01.service;

import com.example.jsf01.model.Employee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class EmployeeService {
    @PersistenceContext
    private EntityManager entityManager;
    public void create(Employee employee) {
        entityManager.persist(employee);
    }
    public List<Employee> list() {
        return entityManager
                .createQuery("FROM Employee e", Employee.class)
                .getResultList();
    }
}
