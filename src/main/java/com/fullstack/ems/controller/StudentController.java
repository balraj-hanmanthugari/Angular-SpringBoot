package com.fullstack.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.fullstack.ems.entity.Student;
import com.fullstack.ems.service.StudentServiceImpl;

@RestController
@RequestMapping("/ems/v1")
public class StudentController {
	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@GetMapping(value = "/student")
	@ResponseStatus(HttpStatus.OK)
	public List<Student> getStudents() {
		return studentServiceImpl.getStudents();
	}

	@GetMapping(value = "/student/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Student> getStudent(@PathVariable Long id) {
		return studentServiceImpl.getStudent(id);
	}

	@PostMapping(value = "/student")
	@ResponseStatus(HttpStatus.CREATED)
	public Student saveStudent(@RequestBody Student student) {
		return studentServiceImpl.saveStudent(student);
	}

	@PutMapping(value = "/student/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
		student.setId(id);
		return studentServiceImpl.updateStudent(id, student);
	}

	@PatchMapping(value = "/student/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Student updatePartialStudent(@PathVariable Long id, @RequestBody Student student) {
		student.setId(id);
		return studentServiceImpl.updateStudentPartial(id, student);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/student/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletStudent(@PathVariable Long id) {
		studentServiceImpl.deleteStudent(id);
		return;
	}
}
