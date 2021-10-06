package com.institution.classroom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.institution.classroom.model.Student;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer>{

	@Query(value = "SELECT s FROM Student s WHERE s.matriculationNumber = :id")
	Student findStudentById(@Param("id") Integer id);

	@Query(value = "SELECT s FROM Student s WHERE s.finalGrade = :finalGrade ORDER BY s.studentName ASC")
	List<Student> getStudentDetailsByGrade(String finalGrade);
}
