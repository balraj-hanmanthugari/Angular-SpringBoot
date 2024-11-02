package com.fullstack.ems.service;

import java.util.List;
import java.util.Optional;

import com.fullstack.ems.entity.Teacher;

public interface TeacherService {
	List<Teacher> getTeachers();

	Optional<Teacher> getTeacher(Long id);

	Teacher saveTeacher(Teacher college);

	Teacher updateTeacher(Long id, Teacher college);

	void deleteTeacher(Long id);
	
	String getSubjects(Long id);
	
	String getSubjects();
}
