///EmployeeService prev implementation
package com.example.jsf01.service;

import com.example.jsf01.model.Employee;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class EmployeeService {
    @PersistenceContext
    private EntityManager entityManager;

    ///@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Employee employee) {
        entityManager.persist(employee);
    }

    public List<Employee> list() {
        return entityManager
                .createQuery("FROM Employee e", Employee.class)
                .getResultList();
    }

    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean updateEmployee(Employee employee) {
        try {
            entityManager.merge(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteEmployee(Long id) {
        System.out.println("### ### ### Entered deleteEmployee by id");
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            try {
                entityManager.remove(employee);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteEmployee(Employee employee) {
        System.out.println("### ### ### Entered deleteEmployee by Employee");
        try {
            entityManager.remove(employee);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
