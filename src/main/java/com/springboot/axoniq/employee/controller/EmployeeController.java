package com.springboot.axoniq.employee.controller;

import com.springboot.axoniq.configuration.ResponseWithError;
import com.springboot.axoniq.employee.query.Employee;
import com.springboot.axoniq.employee.service.EmployeeService;
import com.springboot.axoniq.employee.util.EmployeeDto;
import com.springboot.axoniq.util.AllPageResponse;
import com.springboot.axoniq.util.PageRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.springboot.axoniq.employee.util.EmployeeConverter.*;

/**
 * @author prabhakar, @Date 02-08-2024
 */
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @PostMapping(value = "/create")
    public CompletableFuture<String> createEmployee(@RequestBody EmployeeDto employeeDto){
        var result = createEmployeeCommand(employeeDto);
        return service.createEmployee(result);
    }

    @PutMapping(value = "/updateEmployee")
    public CompletableFuture<Employee> updateEmployee(@RequestBody EmployeeDto employeeDto){
        var result = updateEmployeeCommand(employeeDto);
        return service.updateEmployee(result);
    }

    @DeleteMapping(value = "/deleteEmployee/{id}")
    public CompletableFuture<String> deleteEmployee(@PathVariable String id){
        var result = deleteEmployeeCommand(id);
        return this.service.deleteEmployee(result);
    }

    @GetMapping(value = "/getEmployee/{id}")
    public CompletableFuture<Employee> getEmployee(@PathVariable String id){
        return this.service.getEmployee(id);
    }

    @GetMapping(value = "/getAllEmployees")
    public CompletableFuture<List<Employee>> getAllEmployees(){
        return this.service.getAllEmployees();
    }


    @PutMapping("/searchByNamePagination")
    ResponseWithError<CompletableFuture<List<AllPageResponse>>> findBySearchString(@RequestBody PageRequestDto pageRequestDto){
        return this.service.findBySearchString(pageRequestDto);
    }
}
