package com.springboot.axoniq.employee.query;

import com.springboot.axoniq.employee.commands.DeleteEmployeeCommand;
import com.springboot.axoniq.employee.events.EmployeeCreatedEvent;
import com.springboot.axoniq.employee.events.EmployeeDeletedEvent;
import com.springboot.axoniq.employee.events.EmployeeUpdatedEvent;
import com.springboot.axoniq.util.AllPageResponse;
import com.springboot.axoniq.util.PageRequestDto;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void deleteEmployee(EmployeeDeletedEvent event){
        final var present = this.repository.findById(event.getId());
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


    @QueryHandler(queryName = "searchByNamePagination")
    public AllPageResponse findByPagination(PageRequestDto pageRequestDto){
        Page<Employee> result;

        PageRequest pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(),
                Sort.by("name").ascending());

        if (pageRequestDto.getSearch() != null && !pageRequestDto.getSearch().isEmpty()){
            result = this.repository.findBySearchString(pageRequestDto.getSearch(), pageRequest);
        }else {
            result = this.repository.findAll(pageRequest);
        }

        if (result != null && !result.getContent().isEmpty()){
            List<Employee> records = result.getContent();
            return new AllPageResponse(result.getTotalElements(),records);
        }else
            return new AllPageResponse();

    }


}
