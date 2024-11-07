package com.fullstack.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.fullstack.ems.entity.Employee;

public interface EmployeeService {
	List<Employee> getEmployees();

	Optional<Employee> getEmployee(Long id);

	Employee saveEmployee(Employee employee);

	Employee updateEmployeePartial(Long id, Employee employee);

	void deleteEmployee(Long id);
}
