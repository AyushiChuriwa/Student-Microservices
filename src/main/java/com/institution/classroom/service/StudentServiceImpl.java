package com.institution.classroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.institution.classroom.model.Student;
import com.institution.classroom.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student findStudentById(Integer id) {
		return studentRepository.findStudentById(id);
	}

	@Override
	public Optional<Student> findStudentDetailsById(Integer id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student addStudent(Student student) {
		/*
		 * Applying Grading system
		 * A - 91-100
		 * B - 81-90
		 * C - 71-80
		 * D - 61-70
		 * E - 51-60
		 * Fail - 50
		 * */

		if(student.getSubjectsTaken().size()>0  && student.getTotalMarks() <= student.getSubjectsTaken().size()*100)
		{
			int totalMarks = student.getTotalMarks();
			int averageMarks = totalMarks/(student.getSubjectsTaken().size()); //rounded off average marks per subject
			student.setFinalGrade(averageMarks > 90 ? "A": averageMarks > 80 ? "B": averageMarks > 70 ? "C": 
				averageMarks > 60 ? "D": averageMarks > 50 ? "E": "F");
		}

		Student stud = studentRepository.save(student);		
		return stud;
	}

	@Override
	public void deleteById(Integer id) {
		studentRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		studentRepository.deleteAll();
	}

	@Override
	public List<Student> getStudentDetailsByGrade(String finalGrade) {
		return studentRepository.getStudentDetailsByGrade(finalGrade);
	}

	@Override
	public Student updateStudent(Integer id, Student student) {
		Optional<Student> studentDetails = studentRepository.findById(id);

		if (studentDetails.isPresent()) {
			student.setMatriculationNumber(id);
			
			if(student.getCurrentSemester() == null)
				student.setCurrentSemester(studentDetails.get().getCurrentSemester());
			if(student.getStudentAddress() == null)
				student.setStudentAddress(studentDetails.get().getStudentAddress());
			if(student.getStudentEmail() == null)
				student.setStudentEmail(studentDetails.get().getStudentEmail());
			if(student.getStudentName() == null)
					student.setStudentName(studentDetails.get().getStudentName());
			if(student.getSubjectsTaken() == null)
				student.setSubjectsTaken(studentDetails.get().getSubjectsTaken());
			if(student.getTotalMarks() != null)
			{
				int totalMarks = student.getTotalMarks();
				int averageMarks = totalMarks/student.getSubjectsTaken().size(); //rounded off average marks per subject
				student.setFinalGrade(averageMarks > 90 ? "A": averageMarks > 80 ? "B": averageMarks > 70 ? "C": 
					averageMarks > 60 ? "D": averageMarks > 50 ? "E": "F");
			}
			else {
				student.setTotalMarks(studentDetails.get().getTotalMarks());
				student.setFinalGrade(studentDetails.get().getFinalGrade());
			}
			return studentRepository.save(student);
		}
		return null;
	}

}
