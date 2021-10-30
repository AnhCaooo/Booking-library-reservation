package com.example.bookingReservation;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookingReservation.domain.Room;
import com.example.bookingReservation.domain.RoomRepository;
import com.example.bookingReservation.domain.Student;
import com.example.bookingReservation.domain.StudentRepository;
import com.example.bookingReservation.domain.User;
import com.example.bookingReservation.domain.UserRepository;

@SpringBootApplication
public class BookingReservationApplication {

	private static final Logger Log = LoggerFactory.getLogger(BookingReservationApplication.class); 
	
	public static void main(String[] args) {
		SpringApplication.run(BookingReservationApplication.class, args);
	}

	@Bean 
	public CommandLineRunner studentDemo(StudentRepository studentRepository,
			RoomRepository roomRepository, UserRepository userRepository) {
		return args -> {
			// Save demo data to database
			Log.info("Saving some students who booked the reservation");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			roomRepository.save(new Room("Individual room")); 
			roomRepository.save(new Room("2-5 persons room")); 
			roomRepository.save(new Room("5-14 persons room")); 
			
			
			studentRepository.save(new Student("Cao Tuan Anh", "Male", 2103171, "13:00", "15:00",
					format.parse("2021-10-24"),
					roomRepository.findRoomByName("Individual room").get(0)));
			studentRepository.save(new Student("Anthony Max", "Male", 2002156, "08:00", "10:00", 
					format.parse("2021-10-24"),
					roomRepository.findRoomByName("2-5 persons room").get(0)));
			studentRepository.save(new Student("Kendall Lucy", "Female", 160098, "10:30", "12:30",
					format.parse("2021-10-24"),
					roomRepository.findRoomByName("Individual room").get(0)));
			studentRepository.save(new Student("Zhong Hao", "Female", 140214, "15:30", "17:30",
					format.parse("2021-10-24"),
					roomRepository.findRoomByName("5-14 persons room").get(0)));
			studentRepository.save(new Student("Alesk Mike", "Male", 190232, "18:00", "19:00",
					format.parse("2021-10-24"),
					roomRepository.findRoomByName("2-5 persons room").get(0)));
			
			userRepository.save(new User("user", "$2a$10$/PN5TqnuJDSzBpWotNn9B.HwIU3HRZx5z/rswgTIJpoX9QQ28OYoe" ,"USER", "user@gmail.com"));
			userRepository.save(new User("admin", "$2a$10$Oa2QKYGoIj.rE.l7GAHzQe3zWxKYT4S8qGfj3Mq6pjnGRf1qbC926" ,"ADMIN", "admin@gmail.com"));
		};
	}
}
