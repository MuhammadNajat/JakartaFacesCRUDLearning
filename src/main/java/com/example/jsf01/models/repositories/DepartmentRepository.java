package com.example.jsf01.models.repositories;

import com.example.jsf01.common.models.repositories.GenericRepository;
import com.example.jsf01.models.entities.Department;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;

import java.util.Optional;

import static jakarta.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class DepartmentRepository extends GenericRepository<Department> {

    public DepartmentRepository() {
        super(Department.class);
    }

    @TransactionAttribute(REQUIRED)
    public void delete(Department department) {
        if (getEntityManager().contains(department)) {
            getEntityManager().remove(department);
        } else {
            Optional<Department> managedUser = findById(department.getId());
            managedUser.ifPresent(value -> getEntityManager().remove(value));
        }
    }

}
