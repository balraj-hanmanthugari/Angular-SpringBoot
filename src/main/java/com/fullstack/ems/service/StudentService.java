package com.fullstack.ems.service;

import java.util.List;
import java.util.Optional;

import com.fullstack.ems.entity.Student;

public interface StudentService {
	List<Student> getStudents();

	Optional<Student> getStudent(Long id);

	Student saveStudent(Student student);

	Student updateStudent(Long id, Student student);

	Student updateStudentPartial(Long id, Student student);

	void deleteStudent(Long id);
}
