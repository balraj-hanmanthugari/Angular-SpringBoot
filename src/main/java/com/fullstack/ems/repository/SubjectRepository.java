package com.fullstack.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.ems.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
