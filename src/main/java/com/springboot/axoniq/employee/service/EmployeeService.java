package com.springboot.axoniq.employee.service;

import com.springboot.axoniq.configuration.ResponseWithError;
import com.springboot.axoniq.employee.commands.CreateEmployeeCommand;
import com.springboot.axoniq.employee.commands.DeleteEmployeeCommand;
import com.springboot.axoniq.employee.commands.UpdateEmployeeCommand;
import com.springboot.axoniq.employee.query.Employee;
import com.springboot.axoniq.employee.query.EmployeeRepository;
import com.springboot.axoniq.util.AllPageResponse;
import com.springboot.axoniq.util.PageRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author prabhakar, @Date 02-08-2024
 */

@Service
public class EmployeeService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    private final EmployeeRepository repository;

    public EmployeeService(CommandGateway commandGateway, QueryGateway queryGateway, EmployeeRepository repository) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.repository = repository;
    }


    public CompletableFuture<String> createEmployee(CreateEmployeeCommand command) {
        commandGateway.send(command);
        return CompletableFuture.completedFuture(command.getId());
    }

    public CompletableFuture<Employee> updateEmployee(UpdateEmployeeCommand command) {
        if (this.repository.findById(command.getId()).isPresent()) {
            commandGateway.send(command);
            return CompletableFuture.completedFuture(new Employee(command.getId(),
                    command.getCode(), command.getName(), command.getCompany(),
                    command.getSalary()));
        }
        return CompletableFuture.completedFuture(new Employee());
    }

    public CompletableFuture<String> deleteEmployee(DeleteEmployeeCommand command) {
        if (this.repository.findById(command.getId()).isPresent()) {
            commandGateway.send(command);
            return CompletableFuture.completedFuture("Employee deleted: " + command.getId());
        }else
            return CompletableFuture.completedFuture("Not Found Record.");
    }

    public CompletableFuture<Employee> getEmployee(String id) {
        return queryGateway.query("getEmployee", id, ResponseTypes.instanceOf(Employee.class));
    }

    public CompletableFuture<List<Employee>> getAllEmployees() {
        var result = queryGateway.query("getAllEmployees", "",
                ResponseTypes.multipleInstancesOf(Employee.class)).join();

        return CompletableFuture.completedFuture(result);
    }


    public ResponseWithError<AllPageResponse> findBySearchString(PageRequestDto pageRequestDto){
        var result = queryGateway.query("searchByNamePagination", pageRequestDto,
                ResponseTypes.multipleInstancesOf(AllPageResponse.class)).join().get(0);


        if (result.getTotalRecords() == 0) {
            return ResponseWithError.ofError("No Record Found Here");
        }
        else {
            return ResponseWithError.of(new AllPageResponse(result));
        }
    }



}
