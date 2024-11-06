package com.fullstack.ems.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.ems.common.Constants;
import com.fullstack.ems.common.FileDataUtils;
import com.fullstack.ems.entity.FileData;
import com.fullstack.ems.repository.FileStorageRepository;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private String FileSystemUrl = Constants.FILE_PATH_URL;

	@Autowired
	private FileStorageRepository fileStorageRepository;

	public String uploadImageToDataBase(MultipartFile file) throws IOException {
		FileData imageData = fileStorageRepository.save(FileData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).imageData(FileDataUtils.compressImage(file.getBytes())).build());
		if (imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}

	public byte[] downloadImageFromDatabase(Long id) {
		Optional<FileData> dbImageData = fileStorageRepository.findById(id);
		return FileDataUtils.decompressImage(dbImageData.get().getImageData());
	}

	public String uploadFileToFileSystem(MultipartFile file) throws IOException {
		String filePath = FileSystemUrl + file.getOriginalFilename();

		FileData fileData = fileStorageRepository.save(FileData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).filePath(filePath).build());

		file.transferTo(new File(filePath));

		if (fileData != null) {
			return "file uploaded successfully : " + filePath;
		}
		return null;
	}

	public byte[] downloadFileFromFileSystem(Long fileId) throws IOException {
		Optional<FileData> fileData = fileStorageRepository.findById(fileId);
		String filePath = fileData.get().getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
	}

}
