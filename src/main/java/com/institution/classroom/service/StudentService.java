package com.institution.classroom.service;

import java.util.List;
import java.util.Optional;

import com.institution.classroom.model.Student;

public interface StudentService {

	List<Student> findAllStudents();

	Student findStudentById(Integer id);

	Optional<Student> findStudentDetailsById(Integer id);

	Student addStudent(Student student);

	void deleteById(Integer id);

	void deleteAll();

	List<Student> getStudentDetailsByGrade(String finalGrade);

	Student updateStudent(Integer id, Student student);
}
