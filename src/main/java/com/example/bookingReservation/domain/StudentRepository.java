package com.example.bookingReservation.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository <Student, Long> {
	List<Student> findStudentByStudentNumber(@Param("studentNumber")Integer studentNumber); 
}
