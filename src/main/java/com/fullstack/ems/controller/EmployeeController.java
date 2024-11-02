package com.fullstack.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.ems.entity.Employee;
import com.fullstack.ems.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/ems/v1")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@GetMapping(value = "/employee")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployees() {
		return employeeServiceImpl.getEmployees();
	}

	@GetMapping(value = "/employee/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Employee> getEmployee(@PathVariable Long id) {
		return employeeServiceImpl.getEmployee(id);
	}

	@PostMapping(value = "/employee")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeServiceImpl.saveEmployee(employee);
	}

	@PutMapping(value = "/employee/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return employeeServiceImpl.updateEmployee(id, employee);
	}

	@PatchMapping(value = "/employee/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee updatePartialEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return employeeServiceImpl.updateEmployeePartial(id, employee);
	}

	@DeleteMapping(value = "/employee/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletEmployee(@PathVariable Long id) {
		employeeServiceImpl.deleteEmployee(id);
		return;
	}
}
