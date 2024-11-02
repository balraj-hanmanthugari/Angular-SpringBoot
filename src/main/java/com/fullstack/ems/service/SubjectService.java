package com.fullstack.ems.service;

import java.util.List;
import java.util.Optional;

import com.fullstack.ems.entity.Subject;

public interface SubjectService {
	List<Subject> getSubjects();

	Optional<Subject> getSubject(Long id);

	Subject saveSubject(Subject course);

	Subject updateSubject(Long id, Subject course);

	void deleteSubject(Long id);
}
