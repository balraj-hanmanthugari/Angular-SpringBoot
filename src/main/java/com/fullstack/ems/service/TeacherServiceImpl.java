package com.fullstack.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fullstack.ems.entity.Teacher;
import com.fullstack.ems.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private AbstractEnvironment environment;// all the environment variables

	@Value("${ems}") //only one environment variable
	private String emsUrl;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Optional<Teacher> getTeacher(Long id) {
		return teacherRepository.findById(id);
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher updateTeacher(Long id, Teacher teacher) {
		teacher.setId(id);
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacher(Long id) {
		teacherRepository.deleteById(id);
		return;
	}

	//Using rest template to make api call 
	public String getSubjects(Long id) {
		return restTemplate.getForEntity(environment.getProperty("ems")+"/v1/subject", String.class).getBody();
		//return restTemplate.getForEntity("http://localhost:8080/ems/v1/subject", String.class).getBody();
	}
	
	//Using Web Client to make api call 
	public String getSubjects() {
		WebClient webClient = WebClient.builder().baseUrl(emsUrl).build();
		return webClient.get().uri("/v1/subject").retrieve().bodyToMono(String.class).block();
	}

}
