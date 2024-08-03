package com.springboot.axoniq.employee.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author prabhakar, @Date 02-08-2024
 */
public class EmployeeCreatedEvent {

    @TargetAggregateIdentifier
    private String id;
    private String code;
    private String name;
    private String company;
    private double salary;

    public EmployeeCreatedEvent() {
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
}
