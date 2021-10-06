package com.institution.classroom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.institution.classroom.model.Teacher;
import com.institution.classroom.service.TeacherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "TeacherController", description = "REST APIs related to Teacher Entity.")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@ApiOperation(value = "getAllTeachers", notes="Get all the teachers from the database",nickname = "getAllTeachers")
	@GetMapping(value = "/getAllTeachers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Teacher>> getAllTeachers(@RequestParam(required = false) Integer id) {
		try {
			List<Teacher> teachers = new ArrayList<Teacher>();
			Teacher teacher;
			if (id == null)
				teacherService.findAllTeachers().forEach(teachers::add);
			else {
				teacher = teacherService.findTeacherById(id);
				teachers.add(teacher != null? teacher: null);
			}
			if (teachers.isEmpty()  || teachers.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(teachers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "getTeacherDetailsById", notes="Get a teacher from the database based on his/ her id",nickname = "getTeacherDetailsById")
	@GetMapping(value = "/getTeacherDetailsById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Teacher> getTeacherDetailsById(@PathVariable("id") Integer id) {
		Optional<Teacher> teacher = Optional.ofNullable(teacherService.findTeacherById(id));

		if (teacher.isPresent()) {
			return new ResponseEntity<Teacher>(teacher.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "addTeacher", notes="Add a teacher to the database",nickname = "addTeacher")
	@PostMapping(value = "/addTeacher", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
		try {
			Teacher teach = teacherService.addTeacher(teacher);
			return new ResponseEntity<>(teach, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "deleteTeacher", notes="Delete a teacher from the database based on his/ her id",nickname = "deleteTeacher")
	@DeleteMapping("/deleteTeacher/{id}")
	public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable("id") Integer id) {
		try {
			teacherService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "deleteAllTeachers", notes="Delete all teachers from the database",nickname = "deleteAllTeachers")
	@DeleteMapping("/deleteAllTeachers")
	public ResponseEntity<HttpStatus> deleteAllTeachers() {
		try {
			teacherService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
