package com.example.bookingReservation;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.bookingReservation.domain.Room;
import com.example.bookingReservation.domain.Student;
import com.example.bookingReservation.domain.StudentRepository;

@DataJpaTest
public class StudentRepositoryTest {

	
	@Autowired 
	private StudentRepository studentRepository; 
	
	@Test
	public void findByStudentNumberShouldReturnReservation() {
		List<Student> students = studentRepository.findStudentByStudentNumber(2103171);
		assertThat(students).hasSize(1);
		assertThat(students.get(0).getFullName()).isEqualTo("Cao Tuan Anh");
	}
	
	
	// Test create function 
	@Test 
	public void bookReservation() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Student student = new Student("Lionel Messi", "Male", 1502367, "15:00", "20:00", format.parse("2021-10-24"), new Room("Individual room")); 
		studentRepository.save(student); 
		assertThat(student.getId()).isNotNull();
	}
	

}
