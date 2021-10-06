package com.institution.classroom.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers")
@ApiModel
public class Teacher {

	@ApiModelProperty(notes = "Teacher Id", name="teacherId", required=true)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private Integer teacherId;

	@ApiModelProperty(notes = "Name of the Teacher", name="teacherName", required=true)
	@Column(name = "name")
	private String teacherName;

	@ApiModelProperty(notes = "Email of the Teacher", name="teacherEmail", required=true)
	@Column(name = "email")
	private String teacherEmail;

	@ApiModelProperty(notes = "Address of the Teacher", name="teacherAddress", required=true)
	@Column(name = "address")
	private String teacherAddress;

	@ElementCollection
	@ApiModelProperty(notes = "Subjects taught by the Teacher", name="subjects_taught")
	@Column(name = "subjects") 
	private List<String> subjects_taught;


	@Override
	public String toString() {
		return "Teacher [Teacher Id : " + teacherId + ", name : " + teacherName + ", "
				+ "email : " + teacherEmail + ", address : " + teacherAddress + " + subjects taught : " +
				subjects_taught + "]";
	}
}
