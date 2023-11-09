package com.example.jsf01.models.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Employee implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 1, max = 15)
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can only contain alphabets and spaces")
    @NotNull
    private String firstName;

    @Column(nullable = false)
    @Size(min = 1, max = 15)
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can only contain alphabets and spaces")
    @NotNull
    private String lastName;

    @NotNull
    @Min(1) @Max(200)
    private Integer age;

    @NotNull
    @Min(0) @Max(2000000000)
    private Long salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
