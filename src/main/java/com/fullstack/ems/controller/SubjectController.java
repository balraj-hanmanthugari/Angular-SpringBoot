package com.fullstack.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.ems.entity.Subject;
import com.fullstack.ems.service.SubjectServiceImpl;

@RestController
@RequestMapping("/ems/v1")
public class SubjectController {
	@Autowired
	private SubjectServiceImpl subjectServiceImpl;

	@GetMapping(value = "/subject")
	@ResponseStatus(HttpStatus.OK)
	public List<Subject> getSubjects() {
		return subjectServiceImpl.getSubjects();
	}

	@GetMapping(value = "/subject/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Subject> getSubject(@PathVariable Long id) {
		return subjectServiceImpl.getSubject(id);
	}

	@PostMapping(value = "/subject")
	@ResponseStatus(HttpStatus.CREATED)
	public Subject saveSubject(@RequestBody Subject subject) {
		return subjectServiceImpl.saveSubject(subject);
	}

	@PutMapping(value = "/subject/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
		subject.setId(id);
		return subjectServiceImpl.updateSubject(id, subject);
	}

	@DeleteMapping(value = "/subject/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletSubject(@PathVariable Long id) {
		subjectServiceImpl.deleteSubject(id);
		return;
	}
}
