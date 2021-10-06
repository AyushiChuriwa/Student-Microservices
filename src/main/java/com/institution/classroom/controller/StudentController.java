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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.institution.classroom.model.Student;
import com.institution.classroom.service.StudentService;

import io.swagger.annotations.*;

@Api(value = "StudentController", description = "REST APIs related to Student Entity.")
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@ApiOperation(value = "getAllStudents", notes="Get all the students from the database",nickname = "getAllStudents")
	@GetMapping(value = "/getAllStudents", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) Integer id) {
		try {
			List<Student> students = new ArrayList<Student>();
			Student student;
			if (id == null)
				studentService.findAllStudents().forEach(students::add);
			else {
				student = studentService.findStudentById(id);
				students.add(student != null? student: null);
			}
			if (students.isEmpty()  || students.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "getStudentDetailsById", notes="Get a students from the database based on his/ her id",nickname = "getStudentDetailsById")
	@GetMapping(value = "/getStudentDetailsById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudentDetailsById(@PathVariable("id") Integer id) {
		try{
			Optional<Student> student = studentService.findStudentDetailsById(id);


			if (student.isPresent()) {
				return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "getStudentDetailsByGrade", notes="Fetch al students for a grade",nickname = "getStudentDetailsByGrade")
	@GetMapping(value = "/getStudentDetailsByGrade", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getStudentDetailsByGrade(@RequestParam(name = "finalGrade") String finalGrade) {
		try {
			List<Student> students = studentService.getStudentDetailsByGrade(finalGrade);
			if (students.isEmpty()  || students.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "addStudent", notes="Add a student to the database",nickname = "addStudent")
	@PostMapping(value = "/addStudent", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		try {
			Student stud = studentService.addStudent(student);
			return new ResponseEntity<>(stud, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateStudentDetails")
	public ResponseEntity<Student> updateStudentDetails(@RequestParam(name = "matriculationNumber") Integer id, @RequestBody Student student) {
		try {
			Student stud = studentService.updateStudent(id, student);
			if(stud != null)
				return new ResponseEntity<>(stud, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 

	@ApiOperation(value = "deleteStudent", notes="Delete a student from the database based on his/ her id",nickname = "deleteStudent")
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Integer id) {
		try {
			studentService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "deleteAllStudents", notes="Delete all students from the database",nickname = "deleteAllStudents")
	@DeleteMapping("/deleteAllStudents")
	public ResponseEntity<HttpStatus> deleteAllStudents() {
		try {
			studentService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
