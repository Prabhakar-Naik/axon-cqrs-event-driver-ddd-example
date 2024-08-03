package com.springboot.axoniq.employee.query;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author prabhakar, @Date 02-08-2024
 */
@Entity
public class Employee {
    @Id
    private String id;
    private String code;
    private String name;
    private String company;
    private double salary;

    public Employee() {
    }

    public Employee(String id, String code, String name, String company, double salary) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.company = company;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Employee employee = (Employee) o;
        return Double.compare(getSalary(), employee.getSalary()) == 0 &&
                Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getCode(), employee.getCode()) &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getCompany(), employee.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getName(), getCompany(), getSalary());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                '}';
    }
}

