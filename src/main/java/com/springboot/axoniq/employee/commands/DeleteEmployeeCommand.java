package com.springboot.axoniq.employee.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author prabhakar, @Date 02-08-2024
 */
public class DeleteEmployeeCommand {
    @TargetAggregateIdentifier
    private String id;

    public DeleteEmployeeCommand(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
