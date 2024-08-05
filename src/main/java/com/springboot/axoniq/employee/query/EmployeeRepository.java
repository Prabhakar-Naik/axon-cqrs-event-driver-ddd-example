package com.springboot.axoniq.employee.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

/**
 * @author prabhakar, @Date 02-08-2024
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("select e from Employee e where e.name = :search or e.company = :search or e.code = :search")
    Page<Employee> findBySearchString(@Param("search") String search, Pageable pageRequest);

    @Query("select e from Employee e where e.code = :code")
    Optional<Employee> findByCode(@Param("code") String code);

}
