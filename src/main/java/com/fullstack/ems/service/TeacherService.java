package com.fullstack.ems.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.fullstack.ems.entity.Teacher;

public interface TeacherService {
	Page<Teacher> getTeachers();

	Optional<Teacher> getTeacher(Long id);

	Teacher saveTeacher(Teacher college);

	Teacher updateTeacher(Long id, Teacher college);

	void deleteTeacher(Long id);

	String getSubjects(Long id);

	String getSubjects();
}
