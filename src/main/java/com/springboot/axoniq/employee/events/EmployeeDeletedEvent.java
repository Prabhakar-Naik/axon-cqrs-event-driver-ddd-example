package com.springboot.axoniq.employee.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author prabhakar, @Date 02-08-2024
 */
public class EmployeeDeletedEvent {

    @TargetAggregateIdentifier
    private String id;

    public EmployeeDeletedEvent(){

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

}
