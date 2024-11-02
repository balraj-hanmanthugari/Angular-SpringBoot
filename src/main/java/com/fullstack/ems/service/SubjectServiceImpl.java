package com.fullstack.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.ems.entity.Subject;
import com.fullstack.ems.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<Subject> getSubjects() {
		return subjectRepository.findAll();
	}

	@Override
	public Optional<Subject> getSubject(Long id) {
		return subjectRepository.findById(id);
	}

	@Override
	public Subject saveSubject(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public Subject updateSubject(Long id, Subject subject) {
		subject.setId(id);
		return subjectRepository.save(subject);
	}

	@Override
	public void deleteSubject(Long id) {
		subjectRepository.deleteById(id);
		return;
	}

}
