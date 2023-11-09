package com.example.jsf01.models.repositories;

import com.example.jsf01.common.models.repositories.GenericRepository;
import com.example.jsf01.models.entities.Employee;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;

import java.util.Optional;

import static jakarta.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class EmployeeRepository extends GenericRepository<Employee> {

    public EmployeeRepository() {
        super(Employee.class);
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
