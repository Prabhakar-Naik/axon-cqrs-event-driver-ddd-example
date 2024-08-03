package com.springboot.axoniq.employee.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author prabhakar, @Date 02-08-2024
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
