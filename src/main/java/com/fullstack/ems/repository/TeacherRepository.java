package com.fullstack.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fullstack.ems.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query(value = "SELECT * FROM Teacher u WHERE u.id = :id", nativeQuery = true) //JPQL Query
	Optional<Teacher> findById(@Param("id") Long id);
	
	@Query("SELECT s FROM Student s ORDER BY :name") //JPQL Query
	Optional<Teacher> findByName(@Param("name") String name);
}
