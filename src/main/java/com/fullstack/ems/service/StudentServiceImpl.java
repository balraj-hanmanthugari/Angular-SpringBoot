package com.fullstack.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.ems.entity.Student;
import com.fullstack.ems.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudent(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Long id, Student student) {
		student.setId(id);
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudentPartial(Long id, Student student) {
		Student fetchedStudent = getStudent(id).get();
		if (student.getFirstName() == null) {
			student.setFirstName(fetchedStudent.getFirstName());
		}
		if (student.getLastName() == null) {
			student.setLastName(fetchedStudent.getLastName());
		}
		if (student.getDateOfBirth() == null) {
			student.setDateOfBirth(fetchedStudent.getDateOfBirth());
		}
		if (student.getAddress() == null) {
			student.setAddress(fetchedStudent.getAddress());
		}
		student.setId(id);
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
		return;
	}

}
