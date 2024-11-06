package com.fullstack.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fullstack.ems.entity.Employee;
import com.fullstack.ems.service.EmployeeServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ems/employee")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@GetMapping(value = "/list")
	public String getEmployees(Model model) {
		List<Employee> employeesList = employeeServiceImpl.getEmployees();
		model.addAttribute("employeeList", employeesList);
		return "employee-list";
	}

	@GetMapping(value = "/addForm")
	public ModelAndView getEmployeeAddFom(ModelAndView modelAndView) {
		Employee employee = new Employee();
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("employee-add-form");
		return modelAndView;
	}

	@GetMapping(value = "/updateForm/{id}")
	public String getEmployeeUpdateFom(@PathVariable Long id, ModelMap modelMap) {
		Optional<Employee> employee = employeeServiceImpl.getEmployee(id);
		// supports the use of put method from java.util.Map interface
		modelMap.put("employee", employee);
		return "employee-edit-form";
	}

	@PostMapping(value = "")
	public String saveEmployee(@Valid @ModelAttribute Employee employee, BindingResult bindingResult) {
		if (bindingResult.hasErrors() && employee.getId() != null) {
			return "employee-edit-form";
		} else if (bindingResult.hasErrors()) {
			return "employee-add-form";
		} else {
			employeeServiceImpl.saveEmployee(employee);
			return "redirect:/ems/employee/list";
		}
	}

	@PatchMapping(value = "{id}")
	public String updatePartialEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee.setId(id);
		employeeServiceImpl.updateEmployeePartial(id, employee);
		return "redirect:/ems/employee/list";
	}

	@GetMapping(value = "delete/{id}")
	public String deletEmployee(@PathVariable Long id) {
		employeeServiceImpl.deleteEmployee(id);
		return "redirect:/ems/employee/list";
	}
}
