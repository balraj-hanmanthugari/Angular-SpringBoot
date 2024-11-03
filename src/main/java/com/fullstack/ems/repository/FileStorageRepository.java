package com.fullstack.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.ems.entity.FileData;

public interface FileStorageRepository extends JpaRepository<FileData, Long> {

}
