package com.example.jsf01.services;

import com.example.jsf01.models.entities.Department;
import com.example.jsf01.models.repositories.DepartmentRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class DepartmentService {
    @Inject
    private DepartmentRepository departmentRepository;

    @Transactional
    public void create(Department department) {
        System.out.println("### *** Entered DepartmentService::create");
        departmentRepository.create(department);
    }

    @Transactional
    public Department update(Department department) {
        System.out.println("### *** Entered DepartmentService::update");
        return departmentRepository.update(department);
    }

    @Transactional
    public void delete(Department department) {
        System.out.println("### *** Entered DepartmentService::delete");
        departmentRepository.delete(department);
    }

    @Transactional
    public List<Department> findAll() {
        System.out.println("### *** Entered DepartmentService::findAll");
        List<Department> departments;
        try {
            departments = departmentRepository.findAll().get();
        } catch(Exception e) {
            e.printStackTrace();
            return  null;
        }
        return departments;
    }
}
