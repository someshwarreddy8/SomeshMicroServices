package com.somesh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somesh.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

}
