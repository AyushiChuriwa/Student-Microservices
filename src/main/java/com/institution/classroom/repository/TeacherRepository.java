package com.institution.classroom.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.institution.classroom.model.Teacher;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	@Query(value = "SELECT t FROM Teacher t WHERE t.id = :id")
	Teacher findTeacherById(@Param("id") Integer id);
}
