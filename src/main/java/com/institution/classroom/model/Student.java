package com.institution.classroom.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
@Entity
@Table(name = "students")
@SequenceGenerator(name="matriculationNumberSeq", initialValue=7010000, allocationSize=1)
public class Student {

	@ApiModelProperty(notes = "Matriculation Number of the Student", name="matriculationNumber", required=true)
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="matriculationNumberSeq")
	@NotNull
	private Integer matriculationNumber;

	@ApiModelProperty(notes = "Name of the Student", name="studentName", required=true)
	@Column(name = "name")
	private String studentName;

	@ApiModelProperty(notes = "Email of the Student", name="studentEmail", required=true)
	@Column(name = "email")
	private String studentEmail;

	@ApiModelProperty(notes = "Address of the Student", name="studentAddress", required=true)
	@Column(name = "address")
	private String studentAddress;

	@ApiModelProperty(notes = "Semester the student is currently in", name="currentSemester", required=true)
	@Column(name = "semester")
	private Integer currentSemester;

	@ApiModelProperty(notes = "Subjects the student has taken", name="subjectsTaken")
	@Column(name = "subjectsTaken")
	@ElementCollection
	private List<String> subjectsTaken;

	@ApiModelProperty(notes = "Total Marks of the student", name="totalMarks", required=true)
	@Column(name = "marks") 
	private Integer totalMarks;
	
	@ApiModelProperty(notes = "Final Grade of the student", name="finalGrade")
	@Column(name = "grade") 
	private String finalGrade;

	//	@Override
	//	public String toString() {
	//		return "Student [Matriculation number : " + matriculation_number + ", name : " + student_name + ", "
	//				+ "email : " + student_email + ", address : " + student_address + ", current semester : " 
	//				+ current_semester + ", subjects taken : " + subjects_taken +"]";
	//	}
}
