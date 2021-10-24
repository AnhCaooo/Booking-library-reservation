package com.example.bookingReservation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookingReservation.service.UserDetailServiceImpl;
import com.example.bookingReservation.web.WebController;


@SpringBootTest
public class BookingReservationApplicationTests {

	@Autowired
	private WebController controller; 
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(userDetailServiceImpl).isNotNull();
	}

}
