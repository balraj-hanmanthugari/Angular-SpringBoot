package com.fullstack.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.ems.entity.Employee;
import com.fullstack.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployee(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		employee.setId(id);
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployeePartial(Long id, Employee employee) {
		Employee fetchedEmployee = getEmployee(id).get();
		if (employee.getFirstName() == null) {
			employee.setFirstName(fetchedEmployee.getFirstName());
		}
		if (employee.getLastName() == null) {
			employee.setLastName(fetchedEmployee.getLastName());
		}
		if (employee.getDateOfBirth() == null) {
			employee.setDateOfBirth(fetchedEmployee.getDateOfBirth());
		}
		if (employee.getSalary() == null) {
			employee.setSalary(fetchedEmployee.getSalary());
		}
		employee.setId(id);
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return;
	}

}
