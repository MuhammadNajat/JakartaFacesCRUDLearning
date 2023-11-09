package com.example.jsf01.views;

import com.example.jsf01.models.entities.Department;
import com.example.jsf01.services.DepartmentService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.util.List;

@Named @RequestScoped
public class DepartmentEnrolment {
    private Department department = new Department();
    private List<Department> departments;
    @Inject
    private DepartmentService departmentService;

    @Inject
    private ExternalContext externalContext;
    @PostConstruct
    public void init() {
        try {
            departments = departmentService.findAll();
        } catch(Exception e) {
            e.printStackTrace();
            departments = null;
        }
    }

    public void submit() throws IOException {
        System.out.println("### *** In DepartmentEnrolment::submit");
        departmentService.create(department);
        departments.add(department);

        externalContext.redirect("/JSF01-1.0-SNAPSHOT/home.xhtml");
    }

    public Department getDepartment() {
        return department;
    }
    public List<Department> getDepartments() {
        return departments;
    }
}
