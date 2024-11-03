package com.fullstack.ems.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.ems.entity.Teacher;
import com.fullstack.ems.service.FileStorageServiceImpl;
import com.fullstack.ems.service.TeacherServiceImpl;

@RestController
@RequestMapping("/ems/v1")
public class TeacherController {
	@Autowired
	private TeacherServiceImpl teacherServiceImpl;
	
	@Autowired
	private FileStorageServiceImpl fileStorageServiceImpl;

	@GetMapping(value = "/teacher")
	@ResponseStatus(HttpStatus.OK)
	public List<Teacher> getTeachers() {
		return teacherServiceImpl.getTeachers();
	}

	@GetMapping(value = "/teacher/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Teacher> getTeacher(@PathVariable Long id) {
		return teacherServiceImpl.getTeacher(id);
	}

	@PostMapping(value = "/teacher")
	@ResponseStatus(HttpStatus.CREATED)
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		return teacherServiceImpl.saveTeacher(teacher);
	}

	@PutMapping(value = "/teacher/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Teacher updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
		return teacherServiceImpl.updateTeacher(id, teacher);
	}

	@DeleteMapping(value = "/teacher/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteTeacher(@PathVariable Long id) {
		teacherServiceImpl.deleteTeacher(id);
		return;
	}
	
	//Uses Rest Template
	@GetMapping(value = "/teacher/{id}/subject")
	@ResponseStatus(HttpStatus.OK)
	public String getSubjects(@PathVariable Long id) {
		return teacherServiceImpl.getSubjects(id);
	}
	
	//Uses Web Client
	@GetMapping(value = "/teacher/subject")
	@ResponseStatus(HttpStatus.OK)
	public String getSubjects() {
		return teacherServiceImpl.getSubjects();
	}
	
	@PostMapping(value = "/teacher/image")
	@ResponseStatus(HttpStatus.OK)
	public String uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		return fileStorageServiceImpl.uploadImageToDataBase(file);
	}

	@GetMapping(value = "teacher/image/{fileId}", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody byte[] downloadImage(@PathVariable Long fileId){
		byte[] imageBytes = fileStorageServiceImpl.downloadImageFromDatabase(fileId);
		return imageBytes;
	}
	
	@PostMapping("/teacher/file")
	public String uploadImageToFIleSystem(@RequestParam()MultipartFile file) throws IOException {
		String uploadImage = fileStorageServiceImpl.uploadFileToFileSystem(file);
		return uploadImage;
	}

	@GetMapping("/teacher/file/{fileId}")
	public @ResponseBody byte[] downloadFileFromFileSystem(@PathVariable Long fileId) throws IOException {
		byte[] fileData=fileStorageServiceImpl.downloadFileFromFileSystem(fileId);
		return fileData;
	}

}
