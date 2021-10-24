package com.example.bookingReservation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookingReservation.domain.User;
import com.example.bookingReservation.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	 @Autowired
	    private UserRepository userRepository;
	 
	 @Test 
	 public void findByUserNameShouldReturnUser() {
		 User users = userRepository.findByUsername("user"); 
		 assertThat(users.getRole()).isEqualTo("USER"); 
	 }
	 
	 
}
