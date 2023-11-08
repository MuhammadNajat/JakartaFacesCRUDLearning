package com.example.jsf01.repository;

import com.example.jsf01.model.Employee;
import com.example.jsf01.model.repositories.GenericRepository;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;

import java.util.Optional;

import static jakarta.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class EmployeeRepository extends GenericRepository<Employee> {

    public EmployeeRepository() {
        super(Employee.class);
    }

    public EmployeeRepository(Class<Employee> employeeClass) {
        super(employeeClass);
    }

    @TransactionAttribute(REQUIRED)
    public void delete(Employee employee) {
        if (getEntityManager().contains(employee)) {
            getEntityManager().remove(employee);
        } else {
            Optional<Employee> managedUser = findById(employee.getId());
            managedUser.ifPresent(value -> getEntityManager().remove(value));
        }
    }

}
