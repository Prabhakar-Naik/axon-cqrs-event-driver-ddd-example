package com.springboot.axoniq.employee.aggregate;

import com.springboot.axoniq.employee.commands.CreateEmployeeCommand;
import com.springboot.axoniq.employee.commands.DeleteEmployeeCommand;
import com.springboot.axoniq.employee.commands.UpdateEmployeeCommand;
import com.springboot.axoniq.employee.events.EmployeeCreatedEvent;
import com.springboot.axoniq.employee.events.EmployeeDeletedEvent;
import com.springboot.axoniq.employee.events.EmployeeUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.core.annotation.AliasFor;

/**
 * @author prabhakar, @Date 02-08-2024
 */
@Aggregate
public class EmployeeAggregate {

    @AggregateIdentifier
    private String empId;
    private String code;
    private String name;
    private String company;
    private double salary;

    public EmployeeAggregate() {
    }


    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }


    public String getCompany() {
        return company;
    }


    public double getSalary() {
        return salary;
    }


    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command){
        EmployeeCreatedEvent event = new EmployeeCreatedEvent();
        event.setId(command.getId());
        event.setCode(command.getCode());
        event.setName(command.getName());
        event.setCompany(command.getCompany());
        event.setSalary(command.getSalary());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void createEmployeeEvent(EmployeeCreatedEvent event){
        this.empId = event.getId();
        this.code = event.getCode();
        this.name = event.getName();
        this.company = event.getCompany();
        this.salary = event.getSalary();
    }


    @CommandHandler
    public void updateEmployee(UpdateEmployeeCommand command){
        EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();

        event.setId(command.getId());
        event.setCode(command.getCode());
        event.setName(command.getName());
        event.setCompany(command.getCompany());
        event.setSalary(command.getSalary());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void updateEmployee(EmployeeUpdatedEvent event){
        this.empId = event.getId();
        this.code = event.getCode();
        this.name = event.getName();
        this.company = event.getCompany();
        this.salary = event.getSalary();
    }

    @CommandHandler
    public void deleteEmployee(DeleteEmployeeCommand command){
        EmployeeDeletedEvent event = new EmployeeDeletedEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void deleteEmployee(EmployeeDeletedEvent event){
        this.empId = event.getId();
    }


}
