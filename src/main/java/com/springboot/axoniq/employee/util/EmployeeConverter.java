package com.springboot.axoniq.employee.util;

import com.springboot.axoniq.employee.commands.CreateEmployeeCommand;
import com.springboot.axoniq.employee.commands.DeleteEmployeeCommand;
import com.springboot.axoniq.employee.commands.UpdateEmployeeCommand;

import java.util.UUID;

/**
 * @author prabhakar, @Date 02-08-2024
 */
public class EmployeeConverter {

    public static CreateEmployeeCommand createEmployeeCommand(EmployeeDto employeeDto){
        CreateEmployeeCommand result = new CreateEmployeeCommand();

        result.setId(UUID.randomUUID().toString());
        result.setCode(employeeDto.getCode());
        result.setName(employeeDto.getName());
        result.setCompany(employeeDto.getCompany());
        result.setSalary(employeeDto.getSalary());

        return result;
    }

    public static UpdateEmployeeCommand updateEmployeeCommand(EmployeeDto employeeDto){
        UpdateEmployeeCommand result = new UpdateEmployeeCommand();

        result.setId(employeeDto.getId());
        result.setCode(employeeDto.getCode());
        result.setName(employeeDto.getName());
        result.setCompany(employeeDto.getCompany());
        result.setSalary(employeeDto.getSalary());

        return result;
    }

    public static DeleteEmployeeCommand deleteEmployeeCommand(String id){
        DeleteEmployeeCommand command = new DeleteEmployeeCommand();
        command.setId(id);
        return command;
    }


}
