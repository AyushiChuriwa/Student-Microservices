package com.institution.classroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.institution.classroom.model.Teacher;
import com.institution.classroom.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	TeacherRepository teacherRepository;
	
	@Override
	public List<Teacher> findAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher findTeacherById(Integer id) {
		return teacherRepository.findTeacherById(id);
	}

	@Override
	public Optional<Teacher> findTeacherDetailsById(Integer id) {
		return teacherRepository.findById(id);
	}

	@Override
	public Teacher addTeacher(Teacher teacher) {
		Teacher teach = teacherRepository.save(teacher);		
		return teach;
	}

	@Override
	public void deleteById(Integer id) {
		teacherRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		teacherRepository.deleteAll();
	}

}
