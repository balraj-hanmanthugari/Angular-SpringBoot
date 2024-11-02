package com.fullstack.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.ems.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
