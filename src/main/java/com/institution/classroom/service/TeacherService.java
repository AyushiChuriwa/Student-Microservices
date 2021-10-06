package com.institution.classroom.service;

import java.util.List;
import java.util.Optional;

import com.institution.classroom.model.Teacher;

public interface TeacherService {

	List<Teacher> findAllTeachers();

	Teacher findTeacherById(Integer id);

	Optional<Teacher> findTeacherDetailsById(Integer id);

	Teacher addTeacher(Teacher teacher);

	void deleteById(Integer id);

	void deleteAll();


}
