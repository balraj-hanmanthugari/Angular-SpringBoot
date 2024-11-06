package com.fullstack.ems.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	String uploadImageToDataBase(MultipartFile file) throws IOException;

	byte[] downloadImageFromDatabase(Long id);

	String uploadFileToFileSystem(MultipartFile file) throws IOException;

	byte[] downloadFileFromFileSystem(Long id) throws IOException;
}
