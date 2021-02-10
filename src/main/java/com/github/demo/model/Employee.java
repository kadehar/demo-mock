package com.github.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Employee {
    private @Id @GeneratedValue Long id;
    @NotBlank(message = "Name could not be blank")
    private String name;
    @NotNull(message = "Salary is required")
    private Integer salary;
    @NotNull(message = "Age is required")
    private Integer age;

    public Employee(String name, Integer salary, Integer age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
