package com.springboot.axoniq.employee.query;

import com.springboot.axoniq.employee.events.EmployeeCreatedEvent;
import com.springboot.axoniq.employee.events.EmployeeUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author prabhakar, @Date 02-08-2024
 */
@Component
public class EmployeeProjection {

    @Autowired
    private EmployeeRepository repository;

    @EventHandler
    public void createEmployee(EmployeeCreatedEvent event){
        Employee employee = new Employee();
                employee.setId(event.getId());
                employee.setCode(event.getCode());
                employee.setName(event.getName());
                employee.setCompany(event.getCompany());
                employee.setSalary(event.getSalary());
        this.repository.save(employee);

    }

    @EventHandler
    public void updateEmployee(EmployeeUpdatedEvent event){
        Optional<Employee> employee = this.repository.findById(event.getId());
        if (employee.isPresent()){
            employee.get().setId(event.getId());
            employee.get().setCode(event.getCode());
            employee.get().setName(event.getName());
            employee.get().setCompany(event.getCompany());
            employee.get().setSalary(event.getSalary());
            this.repository.save(employee.get());
        }else
            throw new IllegalArgumentException("Exception");
    }


    @EventHandler
    public void deleteEmployee(String id){
        final var present = this.repository.findById(id);
        present.ifPresent(employee -> this.repository.delete(employee));

    }

    @QueryHandler(queryName = "getEmployee")
    public Employee getEmployee(String id){
        final var present = this.repository.findById(id);
        return present.orElseGet(Employee::new);
    }


    @QueryHandler(queryName = "getAllEmployees")
    public List<Employee> getAllEmployees(){
        return this.repository.findAll();
    }


}
